package com.example.dklearn.admin.fee.service.impl;


import com.example.dklearn.admin.fee.dto.FeeDto;
import com.example.dklearn.admin.fee.dto.FeeResponse;
import com.example.dklearn.admin.fee.model.Fee;
import com.example.dklearn.admin.fee.repository.FeeRepository;
import com.example.dklearn.admin.fee.service.FeeService;
import com.example.dklearn.response.ResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MessageSource messageSource;
    @Override
    public FeeResponse addFee(FeeDto feeDto) {
        ResponseDto responseDto=new ResponseDto();
        FeeResponse feeResponse=new FeeResponse();
        Fee fee=  modelMapper.map(feeDto, Fee.class);
        feeRepository.save(fee);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("fee.add.success",null,Locale.ENGLISH));
        feeResponse.setResponseDto(responseDto);
        return feeResponse;
    }


    @Override
    public FeeResponse getAllFee() {
        ResponseDto responseDto=new ResponseDto();
        FeeResponse feeResponse=new FeeResponse();
        List<Fee> feeList=feeRepository.findAll();
        List<FeeDto> feeList1=  convertFeeToFeeDto(feeList);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("fee.all.success",null,Locale.ENGLISH));
        feeResponse.setResponseDto(responseDto);
        feeResponse.setFeeDtos(feeList1);
        return feeResponse;
    }

    @Override
    public FeeResponse getFee(Long id) {
        ResponseDto responseDto=new ResponseDto();
        FeeResponse feeResponse=new FeeResponse();
        Optional<Fee> fee=  feeRepository.findById(id);
        FeeDto feeDto=  modelMapper.map(fee,FeeDto.class);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("fee.all.success",null, Locale.ENGLISH));
        feeResponse.setResponseDto(responseDto);
        feeResponse.setFeeDto(feeDto);
        return feeResponse;
    }

    public List<FeeDto> convertFeeToFeeDto(List<Fee> feeList){
        List<FeeDto> feeDtos=new ArrayList<>();
        for(Fee fee:feeList){
            FeeDto feeDto= modelMapper.map(fee,FeeDto.class);
            feeDtos.add(feeDto);
        }
        return feeDtos;
    }

}
