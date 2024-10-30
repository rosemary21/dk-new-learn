package com.example.dklearn.customertransaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentMethodDto {
    private String paymentMethod;
    private BigDecimal totalAmount;
    private String currency;
    private BigDecimal shippingFee;


}
