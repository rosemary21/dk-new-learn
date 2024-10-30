package com.example.dklearn.transaction.pendingtransaction.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.card.model.Card;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
public class PendingTransaction extends AbstractEntity {

    private BigDecimal availableAmountToPay;
    private String productDescripCode;
    private BigDecimal totalAmountToPay;
    private String paymentStatus;
    private BigDecimal totalAmountPaid;
    private BigDecimal amount;

    @ManyToOne
    private Card card;

    @ManyToOne
    private DekeralutiveUser dekeralutiveUser;


}
