package com.example.dklearn.transaction.resp;


import com.example.dklearn.customertransaction.model.Transaction;
import com.example.dklearn.response.ResponseDto;
import com.example.dklearn.transaction.dto.TransactionDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {

    private ResponseDto responseDto;
    List<TransactionDto> transactionList;
    TransactionDto transactionDto;
    Transaction transaction;
}
