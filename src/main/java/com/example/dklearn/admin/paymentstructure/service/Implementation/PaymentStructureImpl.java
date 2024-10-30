package com.example.dklearn.admin.paymentstructure.service.Implementation;


import com.example.dklearn.admin.paymentstructure.dto.PaymentStructureDto;
import com.example.dklearn.admin.paymentstructure.model.PaymentStructure;
import com.example.dklearn.admin.paymentstructure.repository.PaymentStructureRepository;
import com.example.dklearn.admin.paymentstructure.resp.PaymentStructureResponse;
import com.example.dklearn.admin.paymentstructure.service.PaymentStructureService;
import com.example.dklearn.response.ResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class PaymentStructureImpl implements PaymentStructureService {
    @Autowired
    PaymentStructureRepository paymentStructureRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MessageSource messageSource;


    @Override
    public PaymentStructureResponse addPaymentStructure(PaymentStructureDto paymentStructureDto) {
        ResponseDto responseDto=new ResponseDto();
        PaymentStructureResponse paymentStructureResponse=new PaymentStructureResponse();
        PaymentStructure paymentStructure=  modelMapper.map(paymentStructureDto, PaymentStructure.class);
        paymentStructureRepository.save(paymentStructure);
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("delivery.add.success",null,Locale.ENGLISH));
        paymentStructureResponse.setResponseDto(responseDto);
        return paymentStructureResponse;
    }

    @Override
    public PaymentStructureResponse selectPaymentStructure(PaymentStructureDto paymentStructureDto) {
        ResponseDto responseDto=new ResponseDto();
        PaymentStructureResponse paymentStructureResponse=new PaymentStructureResponse();
        PaymentStructure paymentStructure=  modelMapper.map(paymentStructureDto, PaymentStructure.class);
        List<PaymentStructure> paymentStructureList= paymentStructureRepository.findByProductDescriptionCode(paymentStructure.getProductDescriptionCode());
        responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("delivery.add.success",null, Locale.ENGLISH));
        paymentStructureResponse.setResponseDto(responseDto);
        paymentStructureResponse.setPaymentStructureList(paymentStructureList);
        return paymentStructureResponse;
    }




}
