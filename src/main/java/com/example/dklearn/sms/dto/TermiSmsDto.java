package com.example.dklearn.sms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TermiSmsDto {
    private String to;
    private String from;
    private String sms;
    private String type;
    private String channel;
    private String api_key;
    private TermiMedia media;
}
