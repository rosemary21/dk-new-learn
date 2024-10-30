package com.example.dklearn.admin.fee.service;


import com.example.dklearn.admin.fee.dto.FeeDto;
import com.example.dklearn.admin.fee.dto.FeeResponse;

public interface FeeService {

    FeeResponse addFee(FeeDto feeDto);

    FeeResponse getAllFee();

    FeeResponse getFee(Long id);
}
