package com.example.dklearn.email.service.implementation;


import com.example.dklearn.email.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class TemplateEmailService {

    @Qualifier("templateEngine")
    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(EmailDto emailDto) {
        // email ID of Recipient.
        Context context = new Context();

        final String username = "70d8c521959305";

        // change accordingly
        final String password = "3756e2ce52475f";
//            final  String password ="48207bda4b3310dd7e16dd5dbb19df34";

        // or IP address
//        final String host = "localhost";

        // Get system properties
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth", "true");

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", "true");

        // Setup mail server
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
//            props.put("mail.smtp.host", "live.smtp.mailtrap.io");

        // TLS Port
        props.put("mail.smtp.port", "2525");
//            props.put("mail.smtp.port", "587");

//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");

        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getInstance(props,
                new Authenticator() {

                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        return new PasswordAuthentication(username,
                                password);
                    }
                });


        try {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress("dkeralutive@gmail.com"));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDto.getEmailAddressTo()));

            // Set Subject: subject of the email
            message.setSubject("This is Subject");
            String emailTemplate= getTemplate(emailDto.getTemplateName());
            if(emailDto.getTemplateName().equals("")){
                context.setVariable("emailAddressTo", emailDto.getEmailAddressTo());
                context.setVariable("transactionId", emailDto.getTransactionId());
            }
            String content = templateEngine.process(emailTemplate, context);

            // set body of the email.
            message.setContent(content, "text/html");

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    private String getTemplate(String response) {
        switch (response) {
            case "CONFIRMED":

                return "email/ConfirmNotification";

            case "ConfirmReceipt":

                return "email/ConfirmReceipt";

            case "DECLINED":

                return "email/DeclineNotification";

            case "UPLOADED":

                return "email/UploadReceipt";

            case "APPROVED":

                return "email/Approve";

            case "SUCCESSFULTRANSACTION":

                return "email/successful";

            default:
                return "email/DeclineNotification";
        }
    }
}

