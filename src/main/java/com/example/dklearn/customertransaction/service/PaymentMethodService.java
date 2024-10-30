package com.example.dklearn.customertransaction.service;

import com.example.dklearn.customertransaction.dto.CustomerTransactionDto;
import com.example.dklearn.customertransaction.dto.TransactionDto;
import com.example.dklearn.customertransaction.resp.TransactionResponse;

import java.util.List;

public interface PaymentMethodService {

    TransactionResponse processTransactionRecord(CustomerTransactionDto transactionDto);
}
