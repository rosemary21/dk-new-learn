package com.example.dklearn.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InitializeTransactionResponse {

    private boolean status;
    private String message;
    private String email;
    private Data data;


}
