package com.payordermicroservice.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private int paymentID;
    private String paymentStatus;
    private String transactionID;
    private int orderID;
    private double amount;

}
