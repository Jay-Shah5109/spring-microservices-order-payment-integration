package com.payordermicroservice.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payordermicroservice.paymentservice.entity.Payment;
import com.payordermicroservice.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionID(UUID.randomUUID().toString());
        log.info("PaymentService request: {}",new ObjectMapper().writeValueAsString(
                payment));
        return paymentRepository.save(payment);
    }

    public String paymentProcessing() {
        // The below API should be from third party payment gateway (PayPal, GPay, PhonePe etc.)
        return new Random().nextBoolean()?"success":"false";
    }


    public Payment findPaymentHistoryByOrderID(int orderID) throws JsonProcessingException {
        Payment payment = paymentRepository.findByOrderID(orderID);
        log.info("PaymentService findPaymentHistoryByOrderID: {}",new ObjectMapper().writeValueAsString(
                payment));
        return payment;
    }
}
