package com.example.dklearn.contactus.service;


import com.example.dklearn.contactus.dto.ContactUsDto;
import com.example.dklearn.contactus.resp.ContactUsResponse;

public interface ContactUsService {

    ContactUsResponse addContact(ContactUsDto contactUsDto);

}
