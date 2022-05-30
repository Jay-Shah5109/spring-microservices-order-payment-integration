package com.payordermicroservice.orderservice.common;

import com.payordermicroservice.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Order order;
    private double amount;
    private String transactionID;
    private String message;
}
