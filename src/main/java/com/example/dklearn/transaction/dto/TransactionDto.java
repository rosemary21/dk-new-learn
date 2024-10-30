package com.example.dklearn.transaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private String currency;
    private String userName;
    private List<TransactionRecordDto> descriptionCodeList;
    private String reference;
    private Long id;
    private String amount;
    private List<MultipartFile> multipartFile;
    private String imageUrl;
    private String transactionStatus;
    private String descriptionCode;
    private String productDescriptionId;
    private String transactionId;

}
