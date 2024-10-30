package com.example.dklearn.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderDto {
    private String orderId;
    private String imageUrl;
    private String productDescription;
    private String productDescripCode;
    private String currency;
    private BigDecimal amount;
    private BigDecimal originalAmount;
    private Integer productDescriptionNumber;
}
