package com.example.dklearn.otp.model;


import com.example.dklearn.otp.dto.OtpDto;
import com.example.dklearn.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class OtpValidator {
    @Autowired
    MessageSource messageSource;

    public ResponseDto validateOtp(OtpDto otpDto) {
        ResponseDto response = new ResponseDto();

        if(otpDto.getOtpType()==null || otpDto.getOtpType().isEmpty()){
            if(response.getMessage()!=null){
                response.setMessage(response.getMessage().concat(" Otp Type is Required"));
            }else {
                response.setMessage(" Otp Type is Required");
            }
            response.setCode("cv96");
        }

        if(otpDto.getNotificationType()==null || otpDto.getNotificationType().isEmpty()){
            if(response.getMessage()!=null){
                response.setMessage(response.getMessage().concat("Notification Type is Required"));
            }else {
                response.setMessage(" Notification Type is Required");
            }
            response.setCode("cv96");
        }
        if(otpDto.getOtpId()==null || otpDto.getOtpId().isEmpty()){
            if(response.getMessage()!=null){
                response.setMessage(response.getMessage().concat("Send Source is Required"));
            }else {
                response.setMessage(" Notification Type is Required");
            }
            response.setCode("cv96");
        }
        return response;
    }


    public ResponseDto validateauthenticatedOtp(OtpDto otpDto){
        ResponseDto response = new ResponseDto();

        if(otpDto.getOtp()==null || otpDto.getOtp().isEmpty()){
            if(response.getMessage()!=null){
                response.setMessage(response.getMessage().concat(" Otp Code is Required"));
            }else {
                response.setMessage(" Otp Code is Required");
            }
            response.setCode("cv96");
        }
        System.out.println("getting the Otp id {}"+otpDto.getOtpId());
        if(otpDto.getOtpId()==null || otpDto.getOtpId().isEmpty()){
            if(response.getMessage()!=null){
                response.setMessage(response.getMessage().concat(" Otp Id is Required"));
            }else {
                response.setMessage(" Otp Id is Required");
            }
            response.setCode("cv96");
        }

        return response;
    }
}
