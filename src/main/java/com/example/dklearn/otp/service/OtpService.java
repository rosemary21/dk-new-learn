package com.example.dklearn.otp.service;


import com.example.dklearn.otp.dto.OtpDto;
import com.example.dklearn.otp.dto.OtpResponseData;

public interface OtpService {

    OtpResponseData authenticateOtp(OtpDto otpDto);
    boolean sendOtp(OtpDto otpDto);
    OtpDto  generateOtp();
    OtpResponseData requestOtp(OtpDto otpDto);

    boolean checkOtp(String otpId,String otp);
    OtpResponseData validateOtp(OtpDto otpDto);
    OtpResponseData getOtpDetails(OtpDto otpDto);
}
