package com.example.dklearn.admin.login.web;


import com.example.dklearn.admin.auth.Jwt;
import com.example.dklearn.admin.login.dto.LoginDto;
import com.example.dklearn.admin.login.resp.LoginResponse;
import com.example.dklearn.admin.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/login", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoginController {
    @Autowired
    LoginService loginService;


    @RequestMapping(value = "/customer" ,method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> validateCustomer(@RequestBody LoginDto loginDto) {
        LoginResponse responseDto= loginService.validateUserNameAndPassword(loginDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            String tokenValue= Jwt.defaultCustomerToken(responseDto.getEmailAddress());
            responseDto.setToken(tokenValue);
            return new ResponseEntity<LoginResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<LoginResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/staff" ,method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> validateStaff(@RequestBody LoginDto loginDto) {
        LoginResponse responseDto= loginService.validateStaffUserNameAndPassword(loginDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            String tokenValue= Jwt.defaultTutorToken(responseDto.getEmailAddress());
            System.out.println("Entering getting the token value {}"+tokenValue);
            responseDto.setToken(tokenValue);
            return new ResponseEntity<LoginResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<LoginResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/admin" ,method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> validateAdmin(@RequestBody LoginDto loginDto) {
        LoginResponse responseDto= loginService.validateAdminUserNameAndPassword(loginDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            String tokenValue= Jwt.defaultTokenAdmin(responseDto.getEmailAddress());
            System.out.println("Entering getting the token value {}"+tokenValue);
            responseDto.setToken(tokenValue);
            return new ResponseEntity<LoginResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<LoginResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
