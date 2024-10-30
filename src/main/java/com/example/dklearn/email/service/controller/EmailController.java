package com.example.dklearn.email.service.controller;


import com.example.dklearn.email.dto.EmailDto;
import com.example.dklearn.email.service.EmailService;
import com.example.dklearn.email.service.implementation.TemplateEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/email", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EmailController {

    @Autowired
    EmailService emailService;
    @Autowired
    TemplateEmailService templateEmailService;

    @RequestMapping(value = "/send" ,method = RequestMethod.POST)
    public ResponseEntity<String> sendEmail() {
         EmailDto emailDto=new EmailDto();
         emailDto.setEmailAddressTo("chukeluchioma408@yahoo.com");
         emailDto.setTemplateName("ConfirmNotification");
         templateEmailService.sendEmail(emailDto);
         return new ResponseEntity<String>("", HttpStatus.OK);
    }

}
