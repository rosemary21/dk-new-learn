package com.example.dklearn.card.service.serviceimpl;


import com.example.dklearn.card.dto.CardResponse;
import com.example.dklearn.card.dto.PaystackVerifyTransactionResponse;
import com.example.dklearn.card.service.VerifyService;
import com.example.dklearn.response.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

@Service
public class VerifyTranServiceImpl implements VerifyService {

    @Value("${paystack.secret.key.url}")
    private String PAYSTACK_SECRET_KEY;

    @Value("${paystack.base.url}")
    private String PAYSTACK_BASE_URL;

    @Value("${paystack.verify.url}")
    private String PAYSTACK_VERIFY_URL;

    @Autowired
    MessageSource messageSource;


    public CardResponse verifyTransaction(String reference){
        CardResponse cardResponse=new CardResponse();
        ResponseDto responseDto=new ResponseDto();
        PaystackVerifyTransactionResponse paystackresponse = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            System.out.println("Reference "+reference);
            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/"+reference);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + PAYSTACK_SECRET_KEY);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(request);
            ObjectMapper mapper = new ObjectMapper();

            System.out.println("response.getStatusLine()"+ response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                System.out.println("Getting the spring builder"+result.toString());
                paystackresponse = mapper.readValue(result.toString(), PaystackVerifyTransactionResponse.class);
                cardResponse.setPaystackVerifyTransactionResponse(paystackresponse);
                responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
                responseDto.setMessage(response.getEntity().getContent().toString());
                cardResponse.setResponseDto(responseDto);
            } else {
                responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
                responseDto.setMessage(response.getEntity().getContent().toString());
                cardResponse.setResponseDto(responseDto);
            }
            if (paystackresponse == null || !(paystackresponse.isStatus())) {
                responseDto.setCode(messageSource.getMessage("dk.error",null, Locale.ENGLISH));
                responseDto.setMessage(response.getEntity().getContent().toString());
                cardResponse.setResponseDto(responseDto);

            } else if (paystackresponse.getData().getStatus().equals("success")) {
                cardResponse.setPaystackVerifyTransactionResponse(paystackresponse);
                responseDto.setCode(messageSource.getMessage("dk.success",null, Locale.ENGLISH));
                responseDto.setMessage(response.getEntity().getContent().toString());
                cardResponse.setResponseDto(responseDto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
          //  throw new Exception("Internal server error");
        }
        return cardResponse;
    }
}
