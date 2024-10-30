package com.example.dklearn.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String customer_code;
    private String phone;
    private String risk_action;
    private String international_format_phone;
}
