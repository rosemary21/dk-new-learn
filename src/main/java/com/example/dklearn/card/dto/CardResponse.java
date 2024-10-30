package com.example.dklearn.card.dto;

import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardResponse {
    ResponseDto responseDto;
    InitializeTransactionResponse initializeTransactionResponse;
    PaystackVerifyTransactionResponse paystackVerifyTransactionResponse;
}
