package com.example.dklearn.card.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Authorization {
    private String authorization_code;
    private String bin;
    private String last4;
    private String exp_month;
    private String exp_year;
    private String channel;
    private String card_type;
    private String bank;
    private String country_code;
    private String brand;
    private boolean reusable;
    private String signature;
    private String account_name;
    private String authorization_url;
}
