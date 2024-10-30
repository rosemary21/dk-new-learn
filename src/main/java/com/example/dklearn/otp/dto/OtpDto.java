package com.example.dklearn.otp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpDto {
    private Long id;
    private String otp;
    private String otpId;
    private String otpType;
    private String notificationType;
    // private String sendSource;
    private String expireTime;
    private String tranAmount;
    private String sentExpiryTime;
    private boolean validateOtp;
}
