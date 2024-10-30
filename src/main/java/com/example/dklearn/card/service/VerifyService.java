package com.example.dklearn.card.service;


import com.example.dklearn.card.dto.CardResponse;

public interface VerifyService {

    CardResponse verifyTransaction(String reference);
}
