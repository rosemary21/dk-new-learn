package com.example.dklearn.admin.user.service;


import com.example.dklearn.admin.user.dto.ChangePasswordDto;
import com.example.dklearn.admin.user.dto.DekeralutiveUserDto;
import com.example.dklearn.admin.user.resp.UserResponse;

public interface UserService {

    UserResponse addUser(DekeralutiveUserDto dto);
    UserResponse updateUser(DekeralutiveUserDto dto);
    UserResponse deleteUser(DekeralutiveUserDto dto);
    UserResponse changePassword(ChangePasswordDto resetPasswordParam);
    UserResponse getAllUser();
    UserResponse resetPassword(ChangePasswordDto resetPasswordParam);
//    UserResponse getAllUsersByPage(PageDescription pageDescription);
    UserResponse getUserCourses();

}
