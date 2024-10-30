package com.example.dklearn.otp.dto;


import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpResponseData {
    private ResponseDto resp;

    private OtpDto otpDetails;
}
