package com.example.dklearn.card.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class CardDto {

    private BigDecimal amount;
    private String cardType;
    private String channel;
    private String authorizationCode;
    private String signature;
    private String expMonth;
    private String expYear;
    @Lob
    private String paystackResponse;
    private Date lastUpdate;
    private String lastFourDigit;
    private LocalDateTime createdOn;
    private String bankName;
    private String reference;


}
