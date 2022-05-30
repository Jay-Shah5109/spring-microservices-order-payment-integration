package com.payordermicroservice.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payordermicroservice.paymentservice.entity.Payment;
import com.payordermicroservice.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderID}")
    public Payment findPaymentHistoryByOrderID(@PathVariable int orderID) throws JsonProcessingException {
        return paymentService.findPaymentHistoryByOrderID(orderID);
    }


}
