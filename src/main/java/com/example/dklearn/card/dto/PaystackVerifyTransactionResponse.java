package com.example.dklearn.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaystackVerifyTransactionResponse {

    private boolean status;
    private String message;
    private Data data;

}
