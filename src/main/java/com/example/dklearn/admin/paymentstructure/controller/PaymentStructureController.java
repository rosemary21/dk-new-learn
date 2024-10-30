package com.example.dklearn.admin.paymentstructure.controller;


import com.example.dklearn.admin.paymentstructure.dto.PaymentStructureDto;
import com.example.dklearn.admin.paymentstructure.resp.PaymentStructureResponse;
import com.example.dklearn.admin.paymentstructure.service.PaymentStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dl/api/v1/paymentstructure", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PaymentStructureController {


    @Autowired
    PaymentStructureService paymentStructureService;
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<PaymentStructureResponse> addPaymentStructure(@RequestBody PaymentStructureDto paymentStructureDto) {
        PaymentStructureResponse responseDto= paymentStructureService.addPaymentStructure(paymentStructureDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<PaymentStructureResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<PaymentStructureResponse>(responseDto, HttpStatus.BAD_REQUEST);
       }



    @RequestMapping(value = "/select" ,method = RequestMethod.POST)
    public ResponseEntity<PaymentStructureResponse> selectPaymentStructure(@RequestBody PaymentStructureDto paymentStructureDto) {
        PaymentStructureResponse responseDto= paymentStructureService.selectPaymentStructure(paymentStructureDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<PaymentStructureResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<PaymentStructureResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @GetMapping
    public String getLoginPage(Model model){
        return "paystack";
    }





}


