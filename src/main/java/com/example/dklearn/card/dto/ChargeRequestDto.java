package com.example.dklearn.card.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChargeRequestDto {
    private String email;
    private String amount;
    private String authorization_code;
}
