package com.example.dklearn.customertransaction.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
public class Transaction extends AbstractEntity {
    private String courseId;
    private BigDecimal amount;
    private String currency;
    private String reference;
    private String status;
    private String transactionId;
    private String emailAddress;

}
