package com.example.dklearn.card.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Where(clause ="del_Flag='N'")

public class Card extends AbstractEntity {
    private BigDecimal amount;
    private String cardType;
    private String channel;
    private String authorizationCode;
    private String signature;
    private String expMonth;
    private String expYear;
    @Lob
    private String paystackResponse;
    private LocalDateTime lastUpdate;
    private String lastFourDigit;
//    private LocalDateTime createdOn;
    private String bankName;
    private String reference;
    @ManyToOne
    DekeralutiveUser dekeralutiveUser;


}
