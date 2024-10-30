package com.example.dklearn.contactus.controller;


import com.example.dklearn.contactus.dto.ContactUsDto;
import com.example.dklearn.contactus.resp.ContactUsResponse;
import com.example.dklearn.contactus.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/contact", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ContactUsController {

    @Autowired
    ContactUsService contactUsService;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<ContactUsResponse> changePin(@RequestBody ContactUsDto contactUsDto) {
        ContactUsResponse responseDto= contactUsService.addContact(contactUsDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<ContactUsResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<ContactUsResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
