package com.example.dklearn.admin.paymentstructure.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class PaymentStructure extends AbstractEntity {
    private String productDescriptionCode;
    private String periodValue;
    private String periodType;
    private String approvalStatus;
    private String amount;
    private String currencyCode;
    private String totalAmount;
//    @ManyToOne
//    PaymentStructure paymentStructure;
}

