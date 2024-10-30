package com.example.dklearn.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private BigDecimal amount;
    /**
     * the currency for the transaction
     */
    private String currency;
    /**
     * the date the transaction occured
     */
    private String transaction_date;


    private String metadata;

    private String id;
    /**
     * status of transaction
     * if the transaction is successful, status = "success"
     */
    private String status;

    private String access_code;
    /**
     * the unique reference that identifies the transaction
     */
    private String reference;
    /**
     * the type of paystack account the transaction was made, could be "test" or "live"
     */
    private String domain;
    // private String metadata;
    /**
     * details about the transaction or why it failed
     */
    private String gateway_response;
    /**
     * message for invalid request
     */
    private String message;
    /**
     * the channel the transaction was made, could be "bank" or "card"
     */
    private String channel;
    /**
     * the ip adress of the user performing the transaction
     */
    private String ip_address;

    private String authorization_url;
    /**
     *
     */
    private String fees;
    /**
     * the plan code if this transaction was made for a plan
     */
    private String plan;
    /**
     * extra information about this transaction
     */
    /**
     * the date the transaction was paid
     */
    private String paid_at;
    private BigDecimal requested_amount;

    private String created_at;
    private List<History> history;
    private Authorization authorization;
    private Customer customer;
}
