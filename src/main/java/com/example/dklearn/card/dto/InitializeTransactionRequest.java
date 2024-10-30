package com.example.dklearn.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class InitializeTransactionRequest {

    @Digits(integer = 9, fraction = 0)
    private Integer amount;

    private String email;
    private String plan;
    private String reference;
    private String subaccount;
    private String callback_url;
    private Float quantity;
    private Integer invoice_limit;
//    private MetaData metadata;
    private Integer transaction_charge;
    private List<String> channel;







}
