package com.example.dklearn.admin.fee.controller;


import com.example.dklearn.admin.fee.dto.FeeDto;
import com.example.dklearn.admin.fee.dto.FeeResponse;
import com.example.dklearn.admin.fee.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/fee", produces = {MediaType.APPLICATION_JSON_VALUE})

public class FeeController {

    @Autowired
    FeeService feeService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<FeeResponse> addFee(@RequestBody FeeDto feeDto) {
        FeeResponse responseDto= feeService.addFee(feeDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<FeeResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<FeeResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }


}
