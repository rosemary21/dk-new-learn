package com.example.dklearn.sms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SmsDto {
    private String dest;
    private String src;
    private String text;
}
