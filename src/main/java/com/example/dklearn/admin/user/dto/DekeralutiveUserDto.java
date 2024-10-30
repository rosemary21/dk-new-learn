package com.example.dklearn.admin.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DekeralutiveUserDto {

    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String userType;
    private String userName;
    private String phoneNumber;
    private Long id;
    private BigDecimal amount;
}
