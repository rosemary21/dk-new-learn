package com.example.dklearn.admin.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordDto {

    private String emailAddress;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String userType;
    private String phoneNumber;
    private String clientId;
    private String otp;
    private String deviceId;
    private String deviceName;
    private String ipAddress;
    private String passwordHash;
}
