package com.example.dklearn.order.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class TransactionOrder extends AbstractEntity {

    private String orderReferenceNumber;
    private String imageUrl;
    private String productDescription;
    private String productDescripCode;
    private String currency;
    private BigDecimal amount;
    private BigDecimal originalAmount;
    private Integer productDescriptionNumber;
    private LocalDateTime orderDate;
    private String orderStatus;

}
