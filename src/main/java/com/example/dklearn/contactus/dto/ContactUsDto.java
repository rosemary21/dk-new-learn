package com.example.dklearn.contactus.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Setter
@Getter
public class ContactUsDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    @Lob
    private String message;
}
