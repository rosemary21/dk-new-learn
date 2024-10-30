package com.example.dklearn.transaction.controller;


import com.example.dklearn.transaction.dto.TransactionDto;
import com.example.dklearn.transaction.dto.TransactionRecordDto;
import com.example.dklearn.transaction.resp.TransactionResponse;
import com.example.dklearn.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dl/api/v1/transaction", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<TransactionResponse> addTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionResponse responseDto= transactionService.addTransactionRecord(transactionDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/process" ,method = RequestMethod.POST)
//    public ResponseEntity<TransactionResponse> processTransaction(@RequestBody TransactionDto transactionDto) {
//        TransactionResponse responseDto= transactionService.processTransactionRecord(transactionDto);
//        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
//    }

//    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
//        public ResponseEntity<TransactionResponse> uploadTransaction(@RequestParam("files") List<MultipartFile> files, @RequestParam("description") String description, @RequestParam("amount") String amount, @RequestParam("currency") String currency, @RequestParam("userName") String userName) {
//        TransactionDto transactionDto=new TransactionDto();
//        TransactionRecordDto transactionRecordDto=new TransactionRecordDto();
//        transactionRecordDto.setProductDescriptionCode(description);
//        transactionDto.setUserName(userName);
//        transactionRecordDto.setAmount(new BigDecimal(amount));
//        transactionDto.setCurrency(currency);
//        List<TransactionRecordDto> t=new ArrayList<>();
//        t.add(transactionRecordDto);
//        transactionDto.setMultipartFile(files);
//        transactionDto.setDescriptionCodeList(t);
//        TransactionResponse responseDto= transactionService.uploadTransaction(transactionDto);
//        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
//    }


    @RequestMapping(value = "/approve" ,method = RequestMethod.POST)
    public ResponseEntity<TransactionResponse> approveTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionResponse responseDto= transactionService.approveTransaction(transactionDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/status" ,method = RequestMethod.POST)
    public ResponseEntity<TransactionResponse> changeTransactionStatus(@RequestBody TransactionDto transactionDto) {
        TransactionResponse responseDto= transactionService.ChangeTransaction(transactionDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/id" ,method = RequestMethod.GET)
    public ResponseEntity<TransactionResponse> getTransactionById(@RequestParam(name = "Id")String Id) {
        TransactionResponse responseDto= transactionService.getTransactionById(Id);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/all/page" ,method = RequestMethod.POST)
//    public ResponseEntity<TransactionResponse> getAllTransactionByPage(@RequestBody PageDescription pageDescription) {
//        TransactionResponse responseDto= transactionService.getAllTransactionByPage(pageDescription);
//        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
//    }
//
//    @RequestMapping(value = "/all/property/page" ,method = RequestMethod.POST)
//    public ResponseEntity<TransactionResponse> getAllPropertyTransactionByPage(@RequestBody PageDescription pageDescription) {
//        TransactionResponse responseDto= transactionService.getAllTransactionByPage(pageDescription);
//        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<TransactionResponse>(responseDto, HttpStatus.BAD_REQUEST);
//    }






}






