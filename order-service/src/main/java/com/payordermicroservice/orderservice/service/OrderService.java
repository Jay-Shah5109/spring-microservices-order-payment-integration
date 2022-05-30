package com.payordermicroservice.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payordermicroservice.orderservice.common.Payment;
import com.payordermicroservice.orderservice.common.TransactionRequest;
import com.payordermicroservice.orderservice.common.TransactionResponse;
import com.payordermicroservice.orderservice.entity.Order;
import com.payordermicroservice.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String PAYMENT_MICROSVC_ENDPOINT_URL;

    private Logger log = LoggerFactory.getLogger(OrderService.class);

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {

        String response = "";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderID(order.getId());
        payment.setAmount(order.getPrice());

        log.info("OrderService request: {}",new ObjectMapper().writeValueAsString(
                transactionRequest));

        // rest API call
        Payment paymentResponse = restTemplate.postForObject(
                PAYMENT_MICROSVC_ENDPOINT_URL, payment, Payment.class);

        log.info("Payment-Service Response from OrderService rest call: {}",new ObjectMapper()
                .writeValueAsString(paymentResponse));

        response = paymentResponse.getPaymentStatus().equals("success")?"Payment processing " +
                "successful" +
                " and order has been placed in cart.":"Payment failure. Order has been added " +
                "to cart.";
        orderRepository.save(order);

        return new TransactionResponse(order, paymentResponse.getAmount(),
                paymentResponse.getTransactionID(),response);
    }
}
