package com.example.dklearn.customertransaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomerTransactionDto {

    private String reference;

    List<TransactionDto> transactionDtoList;
}
