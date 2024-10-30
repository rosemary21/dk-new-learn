package com.example.dklearn.logout.controller;


import com.example.dklearn.logout.dto.LoginResponseData;
import com.example.dklearn.logout.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/dl/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LogoutController {

    @Autowired
    LogoutService logoutService;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<LoginResponseData> logoutUser(HttpServletRequest request){
        var loginResp= logoutService.logout(request);
        if(loginResp.getResp().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<LoginResponseData>(loginResp, HttpStatus.OK);
        }
        else
            return new ResponseEntity<LoginResponseData>(loginResp, HttpStatus.BAD_REQUEST);
    }




}
