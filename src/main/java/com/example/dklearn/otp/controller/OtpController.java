package com.example.dklearn.otp.controller;


import com.example.dklearn.otp.dto.OtpDto;
import com.example.dklearn.otp.dto.OtpResponse;
import com.example.dklearn.otp.dto.OtpResponseData;
import com.example.dklearn.otp.model.OtpValidator;
import com.example.dklearn.otp.service.OtpService;
import com.example.dklearn.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = {"/dl/api/v1/otp"}, produces = {MediaType.APPLICATION_JSON_VALUE})

public class OtpController {

    @Autowired
    OtpService otpService;

    @PostMapping("/request")
    public ResponseEntity<OtpResponse> requestOtpViaSms(@RequestBody OtpDto otpDto) {
        OtpValidator otpValidator=new OtpValidator();
        OtpResponseData otpResponseData=new OtpResponseData();
        OtpResponse otpResponse=new OtpResponse();
        ResponseDto erorrResponseDto=otpValidator.validateOtp(otpDto);
        otpResponseData.setResp(erorrResponseDto);
        if(erorrResponseDto.getCode()!=null && !(erorrResponseDto.getCode().isEmpty())){
            if(erorrResponseDto.getCode().equalsIgnoreCase("cv96")){
                otpResponse=otpResponse.errorResponse(otpResponseData);
                return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.BAD_REQUEST);
            }
        }
        OtpResponseData responseDto= otpService.requestOtp(otpDto);
        log.info("getting the response dto{}",responseDto.getResp().getMessage());
        if(responseDto.getResp().getCode().equalsIgnoreCase("dk00")){
            otpResponse= otpResponse.successResponse(responseDto);
            return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.OK);
        }
        otpResponse= otpResponse.errorResponse(responseDto);
        return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<OtpResponse> validateauthenticatedOtp(@RequestBody OtpDto otpDto) {
        OtpValidator otpValidator=new OtpValidator();
        OtpResponse otpResponse=new OtpResponse();
        OtpResponseData otpResponseData=new OtpResponseData();
        ResponseDto erorrResponseDto=  otpValidator.validateauthenticatedOtp(otpDto);
        otpResponseData.setResp(erorrResponseDto);
        if(erorrResponseDto.getCode()!=null && !(erorrResponseDto.getCode().isEmpty())){
            if(erorrResponseDto.getCode().equalsIgnoreCase("dk96")){
                otpResponse= otpResponse.errorResponse(otpResponseData);
                return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.BAD_REQUEST);
            }
        }

        OtpResponseData responseDto= otpService.authenticateOtp(otpDto);
        log.info("getting the response dto{}",responseDto.getResp().getCode());
        if(responseDto.getResp().getCode().equalsIgnoreCase("dk00")){
            otpResponse=otpResponse.successResponse(responseDto);
            return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.OK);
        }
        if(responseDto.getResp().getCode().equalsIgnoreCase("cv401")){
            otpResponse=otpResponse.errorResponse(responseDto);
            return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.FORBIDDEN);
        }
        otpResponse=otpResponse.errorResponse(responseDto);
        log.info("getting the otp response MM {}",responseDto.getResp().getCode());
        return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/validate")
    public ResponseEntity<OtpResponse> validateOtp(@RequestBody OtpDto otpDto) {
        OtpValidator otpValidator=new OtpValidator();
        OtpResponse otpResponse=new OtpResponse();
        OtpResponseData otpResponseData=new OtpResponseData();
        ResponseDto erorrResponseDto=  otpValidator.validateauthenticatedOtp(otpDto);
        otpResponseData.setResp(erorrResponseDto);
        if(erorrResponseDto.getCode()!=null && !(erorrResponseDto.getCode().isEmpty())){
            if(erorrResponseDto.getCode().equalsIgnoreCase("dk96")){
                otpResponse= otpResponse.errorResponse(otpResponseData);
                return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.BAD_REQUEST);
            }
        }
        OtpResponseData responseDto= otpService.validateOtp(otpDto);
        log.info("getting the response dto{}",responseDto.getResp().getCode());
        if(responseDto.getResp().getCode().equalsIgnoreCase("dk00")){
            otpResponse=otpResponse.successResponse(responseDto);
            return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.OK);
        }
        if(responseDto.getResp().getCode().equalsIgnoreCase("cv401")){
            otpResponse=otpResponse.errorResponse(responseDto);
            return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.FORBIDDEN);
        }
        otpResponse=otpResponse.errorResponse(responseDto);
        log.info("getting the otp response MM {}",responseDto.getResp().getCode());
        return new ResponseEntity<OtpResponse>(otpResponse, HttpStatus.BAD_REQUEST);
    }
}
