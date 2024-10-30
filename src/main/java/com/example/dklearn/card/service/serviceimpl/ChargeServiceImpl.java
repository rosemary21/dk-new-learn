package com.example.dklearn.card.service.serviceimpl;


import com.example.dklearn.card.dto.ChargeRequestDto;
import com.example.dklearn.card.dto.CheckChargeResponse;
import com.example.dklearn.card.dto.InitializeTransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ChargeServiceImpl {

    @Value("${paystack.secret.key.url}")
    private String PAYSTACK_SECRET_KEY;

    @Value("${paystack.base.url}")
    private String PAYSTACK_URL;

    @Value("${paystack.charge.url}")
    private String PAYSTACK_CHARGE;

    @Value("${paystack.check.url}")
    private String PAYSTACKCHECKURL;


    public InitializeTransactionResponse chargeTransaction(ChargeRequestDto request) throws Exception {
        InitializeTransactionResponse initializeTransactionResponse = null;
        try {
            // convert transaction to json then use it as a body to post json
            Gson gson = new Gson();
            // add paystack chrges to the amount
            StringEntity postingString = new StringEntity(gson.toJson(request));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(PAYSTACK_URL+PAYSTACK_CHARGE);
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer "+PAYSTACK_SECRET_KEY);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 1) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();

            initializeTransactionResponse = mapper.readValue(result.toString(), InitializeTransactionResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializaing paystack transaction");
        }

        return initializeTransactionResponse;

    }


    public CheckChargeResponse CheckchargeTransaction(ChargeRequestDto request) throws Exception {
        CheckChargeResponse checkChargeResponse = null;
        try {
            // convert transaction to json then use it as a body to post json
            Gson gson = new Gson();
            // add paystack chrges to the amount
            StringEntity postingString = new StringEntity(gson.toJson(request));
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(PAYSTACK_URL+PAYSTACKCHECKURL);
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer "+PAYSTACK_SECRET_KEY);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 1) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new Exception("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();

            checkChargeResponse = mapper.readValue(result.toString(), CheckChargeResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializaing paystack transaction");
        }

        return checkChargeResponse;

    }




}
