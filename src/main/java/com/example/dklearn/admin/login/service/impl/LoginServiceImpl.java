package com.example.dklearn.admin.login.service.impl;


import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.administration.repository.AdminRepository;
import com.example.dklearn.admin.login.dto.LoginDto;
import com.example.dklearn.admin.login.resp.LoginResponse;
import com.example.dklearn.admin.login.service.LoginService;
import com.example.dklearn.admin.staff.model.Staff;
import com.example.dklearn.admin.staff.repository.StaffRepository;
import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.admin.user.repository.UserRepository;
import com.example.dklearn.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class LoginServiceImpl  implements LoginService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public LoginResponse validateUserNameAndPassword(LoginDto loginDto) {
        ResponseDto responseDto=new ResponseDto();
        LoginResponse loginResponse=new LoginResponse();
        DekeralutiveUser dekeralutiveUser=userRepository.findByUserName(loginDto.getUserName());
        log.info("getting the dekeralutive user {}",dekeralutiveUser);
        if(dekeralutiveUser==null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("login.username.no.exist",null,Locale.ENGLISH));
            loginResponse.setResponseDto(responseDto);

            return loginResponse;
        }
        if(dekeralutiveUser!=null){
            log.info("login password {}",loginDto.getPassword());
            log.info("password hash {}",dekeralutiveUser.getPasswordhash());
            if(!(passwordEncoder.matches(loginDto.getPassword(),dekeralutiveUser.getPasswordhash()))){
                responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
                responseDto.setMessage(messageSource.getMessage("login.password.wrong",null,Locale.ENGLISH));
                loginResponse.setResponseDto(responseDto);
                return loginResponse;
            }
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("login.username.success",null,Locale.ENGLISH));
            loginResponse.setEmailAddress(loginDto.getUserName());
            loginResponse.setUserName(dekeralutiveUser.getFullName());
            loginResponse.setResponseDto(responseDto);
            return loginResponse;
        }
        return null;
    }

    @Override
    public LoginResponse validateStaffUserNameAndPassword(LoginDto loginDto) {
        ResponseDto responseDto=new ResponseDto();
        LoginResponse loginResponse=new LoginResponse();
        Staff dekeralutiveUser=staffRepository.findByUserName(loginDto.getUserName());
        log.info("getting the dekeralutive {}",dekeralutiveUser);
        if(dekeralutiveUser==null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("login.username.no.exist",null,Locale.ENGLISH));
            loginResponse.setResponseDto(responseDto);

            return loginResponse;
        }
        if(dekeralutiveUser!=null){
            if(!(passwordEncoder.matches(loginDto.getPassword(),dekeralutiveUser.getPasswordhash()))){
                responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
                responseDto.setMessage(messageSource.getMessage("login.password.wrong",null,Locale.ENGLISH));
                loginResponse.setResponseDto(responseDto);
                return loginResponse;
            }
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("login.username.success",null,Locale.ENGLISH));
            loginResponse.setEmailAddress(loginDto.getUserName());
            loginResponse.setUserName(dekeralutiveUser.getFullName());
            loginResponse.setResponseDto(responseDto);
            return loginResponse;
        }
        return null;
    }



    @Override
    public LoginResponse validateAdminUserNameAndPassword(LoginDto loginDto) {
        ResponseDto responseDto=new ResponseDto();
        LoginResponse loginResponse=new LoginResponse();
        Admin dekeralutiveUser=adminRepository.findByUserName(loginDto.getUserName());
        log.info("getting the dekeralutive {}",dekeralutiveUser);

        if(dekeralutiveUser==null){
            responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("login.username.no.exist",null,Locale.ENGLISH));
            loginResponse.setResponseDto(responseDto);

            return loginResponse;
        }
        if(dekeralutiveUser!=null){
            if(!(passwordEncoder.matches(loginDto.getPassword(),dekeralutiveUser.getPasswordhash()))){
                responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
                responseDto.setMessage(messageSource.getMessage("login.password.wrong",null,Locale.ENGLISH));
                loginResponse.setResponseDto(responseDto);
                return loginResponse;
            }
            responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
            responseDto.setMessage(messageSource.getMessage("login.username.success",null,Locale.ENGLISH));
            loginResponse.setEmailAddress(loginDto.getUserName());
            loginResponse.setUserName(dekeralutiveUser.getFullName());
            loginResponse.setResponseDto(responseDto);
            return loginResponse;
        }
        return null;
    }
//    @Override
//    public boolean validateLoginToken(String token){
//        boolean resultValue=false;
//
//        try{
//            Token value= tokenRepository.findFirstByTokenValue(token);
//            if((value!=null) && (value.getStatus().equals("B"))){
//                resultValue= true;
//            }
//            return resultValue;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return resultValue;
//
//    }
}
