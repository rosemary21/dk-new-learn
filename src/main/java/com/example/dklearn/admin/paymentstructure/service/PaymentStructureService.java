package com.example.dklearn.admin.paymentstructure.service;


import com.example.dklearn.admin.paymentstructure.dto.PaymentStructureDto;
import com.example.dklearn.admin.paymentstructure.resp.PaymentStructureResponse;

public interface PaymentStructureService {

    PaymentStructureResponse addPaymentStructure(PaymentStructureDto paymentStructureDto);

    PaymentStructureResponse selectPaymentStructure(PaymentStructureDto paymentStructureDto);
}
