package com.example.dklearn.admin.paymentstructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentStructureDto {

    private String productDescriptionCode;
    private String periodValue;
    private String periodType;
    private String approvalStatus;
    private String amount;
    private String currencyCode;
    private String initialPayment;
    private String noInstallment;
    private String totalPayment;
    private String email;


}
