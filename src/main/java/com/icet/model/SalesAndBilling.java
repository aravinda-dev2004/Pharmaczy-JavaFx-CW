package com.icet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndBilling {
    private Integer saleId;
    private LocalDate transactionDate;
    private Double totalAmount;
    private String paymentMethod;
    private Double taxAmount;
}
