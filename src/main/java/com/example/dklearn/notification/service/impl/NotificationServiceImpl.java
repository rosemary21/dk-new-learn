package com.example.dklearn.notification.service.impl;



import com.example.dklearn.notification.dto.EmailDto;
import com.example.dklearn.notification.service.NotificationService;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
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
public class NotificationServiceImpl implements NotificationService {

    @Qualifier("templateEngine")
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(EmailDto emailDto) {
        // email ID of Recipient.
          Context context = new Context();
        try {
                System.out.println("paystackurl "+emailDto.getPayStackUrl());
                context.setVariable("emailAddressTo", emailDto.getEmailAddressTo());
                context.setVariable("transactionId", emailDto.getTransactionId());
                context.setVariable("invoiceUrl", emailDto.getInvoiceUrl());
                context.setVariable("payStackUrl", emailDto.getPayStackUrl());
                context.setVariable("customerName", emailDto.getCustomerName());

            String content = templateEngine.process(emailDto.getTemplateName(), context);

            Mailer mailer = MailerBuilder.withSMTPServerHost("smtp.office365.com")
                    .withSMTPServerPort(587)
                    .withSMTPServerUsername("tech@dkeralutive.com")
                    .withSMTPServerPassword("Catdog@123$")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS).buildMailer();


            Email email = EmailBuilder.startingBlank()
                    .from("Dkeralutive", "tech@dkeralutive.com")
                    .to( "chukeluchioma408@yahoo.com", "chukeluchioma408@yahoo.com")
                    .withSubject("Generate Invoice And Payment Link")
                    .withHTMLText(content).buildEmail();

            // Send email.
            mailer.sendMail(email, false);
            System.out.println("Mail successfully sent");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void sendOtpEmail(EmailDto emailDto) {
        // email ID of Recipient.
        Context context = new Context();
        try {
            context.setVariable("emailAddressTo", emailDto.getEmailAddressTo());
            context.setVariable("otp", emailDto.getOtp());
            context.setVariable("expiryDateTime", emailDto.getExpiryDateTime());

            String content = templateEngine.process(emailDto.getTemplateName(), context);

            Mailer mailer = MailerBuilder.withSMTPServerHost("smtp.office365.com")
                    .withSMTPServerPort(587)
                    .withSMTPServerUsername("tech@dkeralutive.com")
                    .withSMTPServerPassword("Catdog@123$")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS).buildMailer();


            Email email = EmailBuilder.startingBlank()
                    .from("Dkeralutive", "tech@dkeralutive.com")
                    .to( emailDto.getEmailAddressTo(), emailDto.getEmailAddressTo())
                    .withSubject("Authentication Code")
                    .withHTMLText(content).buildEmail();

            // Send email.
            mailer.sendMail(email, false);
            System.out.println("Mail successfully sent");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }



}
