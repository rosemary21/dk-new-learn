package com.example.dklearn.notification.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class EmailDto {

    private  String emailAddressTo;
    private String templateName;
    private String transactionId;
    private String invoiceUrl;
    private String payStackUrl;
    private String customerName;
    private String logo;
    private String otp;
    private String expiryDateTime;
}
