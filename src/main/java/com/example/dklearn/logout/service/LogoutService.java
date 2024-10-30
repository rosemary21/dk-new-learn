package com.example.dklearn.logout.service;


import com.example.dklearn.logout.dto.LoginResponseData;
import com.example.dklearn.logout.model.Token;
import com.example.dklearn.logout.repository.TokenRepository;
import com.example.dklearn.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;

@Service
public class LogoutService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    MessageSource messageSource;

    public LoginResponseData logout(HttpServletRequest request){
        LoginResponseData lrData = new LoginResponseData();
        ResponseDto resp = new ResponseDto();
        Token token=new Token();
        String tokenValue=request.getHeader("apiKey");
        token.setTokenValue(tokenValue);
        token.setStatus("B");
        token.setCreatedOn(new Date());
        tokenRepository.save(token);
        resp.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        resp.setMessage(messageSource.getMessage("logout.success.message",null,Locale.ENGLISH));
        lrData.setResp(resp);
        return lrData;
    }


    public boolean validateLoginToken(String token){
        boolean resultValue=false;

        try{
            Token value= tokenRepository.findFirstByTokenValue(token);
            if((value!=null) && (value.getStatus().equals("B"))){
                resultValue= true;
            }
            return resultValue;
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultValue;

    }
}
