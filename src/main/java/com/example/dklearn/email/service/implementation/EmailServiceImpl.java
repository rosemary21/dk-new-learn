package com.example.dklearn.email.service.implementation;


import com.example.dklearn.email.service.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public boolean sendEmailNotification() {
//        final String username = "70d8c521959305";
        final String username="api";

        // change accordingly
//        final String password = "3756e2ce52475f";
        final  String password ="48207bda4b3310dd7e16dd5dbb19df34";

        // or IP address
//        final String host = "localhost";

        // Get system properties
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth", "true");

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", "true");

        // Setup mail server
//        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.host", "live.smtp.mailtrap.io");

        // TLS Port
//        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.port", "587");

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

            // compose the message
            // javax.mail.internet.MimeMessage class is
            // mostly used for abstraction.
            Message message = new MimeMessage(session);

            // header field of the header.
            message.setFrom(new InternetAddress("dkeralutive@gmail.com"));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("chukeluchioma408@yahoo.com"));
            message.setSubject("hello");
            message.setText("Yo it has been sent");

            Transport.send(message);         //send Message

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }




}
