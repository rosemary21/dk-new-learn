package com.example.dklearn.transaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransactionRecordDto {

    private String productDescriptionCode;
    private String productCategoryCode;
    private String productCode;
    private String productDescription;
    private BigDecimal amount;
    private String currency;
    private Integer productDescriptionNumber;
    private String stockId;
    private Long id;
}
