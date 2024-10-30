package com.example.dklearn.admin.fee.dto;


import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FeeResponse {
    private ResponseDto responseDto;
    private List<FeeDto> feeDtos;
    private FeeDto feeDto;
}
