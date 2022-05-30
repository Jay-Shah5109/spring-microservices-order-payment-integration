package com.payordermicroservice.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payordermicroservice.orderservice.common.Payment;
import com.payordermicroservice.orderservice.common.TransactionRequest;
import com.payordermicroservice.orderservice.common.TransactionResponse;
import com.payordermicroservice.orderservice.entity.Order;
import com.payordermicroservice.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
        return orderService.saveOrder(transactionRequest);
        //return orderService.saveOrder(order);
    }
}
