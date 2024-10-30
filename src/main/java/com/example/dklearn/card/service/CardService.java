package com.example.dklearn.card.service;


import com.example.dklearn.card.dto.CardResponse;
import com.example.dklearn.card.dto.InitializeTransactionRequest;
import com.example.dklearn.card.dto.InitializeTransactionResponse;

public interface CardService {

    CardResponse initTransaction(InitializeTransactionRequest request) throws Exception;

    CardResponse addCard(InitializeTransactionResponse initializeTransactionResponse);
}
