package com.example.dklearn.email.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailDto {

   private  String emailAddressTo;
   private String templateName;
   private String transactionId;


}
