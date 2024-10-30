package com.example.dklearn.admin.login.resp;


import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {

    private ResponseDto responseDto;
    private String token;
    private String emailAddress;
    private String userName;

}
