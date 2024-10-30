package com.example.dklearn.admin.paymentstructure.resp;


import com.example.dklearn.admin.paymentstructure.model.PaymentStructure;
import com.example.dklearn.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PaymentStructureResponse {
    private ResponseDto responseDto;

    List<PaymentStructure> paymentStructureList;
}
