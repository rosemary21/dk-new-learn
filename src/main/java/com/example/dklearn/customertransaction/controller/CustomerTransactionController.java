package com.example.dklearn.customertransaction.controller;


import com.example.dklearn.courses.dto.CoursesDto;
import com.example.dklearn.courses.resp.CourseResponse;
import com.example.dklearn.customertransaction.dto.CustomerTransactionDto;
import com.example.dklearn.customertransaction.resp.TransactionResponse;
import com.example.dklearn.customertransaction.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/customertransaction", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerTransactionController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<TransactionResponse> addCourses(@RequestBody CustomerTransactionDto customerTransactionDto){
        com.example.dklearn.customertransaction.resp.TransactionResponse courseResponse=paymentMethodService.processTransactionRecord(customerTransactionDto);
        if(courseResponse.getResponseDto().getCode().equalsIgnoreCase("dk00")){
            return new ResponseEntity<com.example.dklearn.customertransaction.resp.TransactionResponse>(courseResponse, HttpStatus.OK);
        }
        else
            return new ResponseEntity<com.example.dklearn.customertransaction.resp.TransactionResponse>(courseResponse, HttpStatus.BAD_REQUEST);
    }
}
