package com.example.dklearn.admin.login.service;


import com.example.dklearn.admin.login.dto.LoginDto;
import com.example.dklearn.admin.login.resp.LoginResponse;

public interface LoginService {

    LoginResponse validateUserNameAndPassword(LoginDto loginDto);

    LoginResponse validateStaffUserNameAndPassword(LoginDto loginDto);

    LoginResponse validateAdminUserNameAndPassword(LoginDto loginDto);

}
