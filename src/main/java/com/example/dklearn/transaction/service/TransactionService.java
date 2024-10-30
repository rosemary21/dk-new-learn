package com.example.dklearn.transaction.service;


//import com.example.dklearn.admin.productdescription.dto.PageDescription;
import com.example.dklearn.transaction.dto.TransactionDto;
import com.example.dklearn.transaction.resp.TransactionResponse;

public interface TransactionService {


    TransactionResponse addTransactionRecord(TransactionDto transactionDto);
    TransactionResponse processTransactionRecord(TransactionDto transactionDto);
    TransactionResponse approveTransaction(TransactionDto transactionDto);
//    TransactionResponse uploadTransaction(TransactionDto transactionDto);
    //TransactionResponse getAllTransactionByPage(PageDescription pageDescription);
   // TransactionResponse getPropertyAllTransactionByPage(PageDescription pageDescription);

    TransactionResponse ChangeTransaction(TransactionDto transactionDto);
    TransactionResponse getTransactionById(String id);


}
