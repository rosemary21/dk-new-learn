package com.example.dklearn.otp.repository;


import com.example.dklearn.otp.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    Otp findByOtpId(String otpId);

    Otp findByOtpIdAndOtp(String otpId,String otp);
}
