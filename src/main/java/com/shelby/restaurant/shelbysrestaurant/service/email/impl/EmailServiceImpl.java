package com.shelby.restaurant.shelbysrestaurant.service.email.impl;

import com.shelby.restaurant.shelbysrestaurant.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@ConditionalOnProperty(name = "mail.sender.enabled", havingValue = "true")
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MimeMessage mimeMessage;

    @Async
    @Override
    public void send(String to, String subject, String email) {
        log.info("Sending email");
        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(email, "text/html; charset=utf-8");
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email!");
        }
    }
}
