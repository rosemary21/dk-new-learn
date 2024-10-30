package com.example.dklearn.otp.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Otp_details")
public class Otp extends AbstractEntity {
    private String otp;
    private LocalDateTime sentTime;
    private String otpId;
    private String otpType;
    private LocalDateTime expiryTime;
    private boolean validateOtp;
}
