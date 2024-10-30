package com.example.dklearn.customertransaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransactionDto {
    private String reference;
    private BigDecimal amount;
    private String currency;
    private Long courseId;
}
