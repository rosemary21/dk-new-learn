package com.example.dklearn.customertransaction.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import com.example.dklearn.order.model.TransactionOrder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class PaymentMethod  extends AbstractEntity {
    private String paymentMethod;
    private BigDecimal totalAmount;
    private String currency;
    private BigDecimal shippingFee;
    private String orderReferenceNumber;

    @OneToMany
    private List<TransactionOrder> order;

}
