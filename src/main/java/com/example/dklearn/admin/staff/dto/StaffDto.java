package com.example.dklearn.admin.staff.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StaffDto {

    private String fullName;
    private String email;
    private String phoneNumber;
    private int age;
    private String gender;
    private String certicateUrl;
    private  String category;
    private String passwordhash;
    private String userType;
    private String userName;
    private String password;
    private String confirmPassword;
    private Long id;
    private List<AccountDetailDto> accountDetailDtoList;
}
