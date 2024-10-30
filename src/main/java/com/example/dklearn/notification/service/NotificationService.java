package com.example.dklearn.notification.service;


import com.example.dklearn.notification.dto.EmailDto;

public interface NotificationService {
    void sendEmail(EmailDto emailDto);

    void sendOtpEmail(EmailDto emailDto);
}
