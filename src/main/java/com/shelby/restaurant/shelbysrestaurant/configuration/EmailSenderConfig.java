package com.shelby.restaurant.shelbysrestaurant.configuration;

import com.shelby.restaurant.shelbysrestaurant.util.EmailFields;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
public class EmailSenderConfig {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${mail.smtp.auth}")
    private String auth;

    @Value("${mail.smtp.starttls.enable}")
    private String starttls;

    @Value("${mail.user}")
    private String user;

    @Value("${mail.password}")
    private String password;

    @Bean
    public MimeMessage mimeMessage() throws MessagingException {
        final Properties properties = new Properties();
        properties.put(EmailFields.HOST, host);
        properties.put(EmailFields.PORT, port);
        properties.put(EmailFields.AUTH, auth);
        properties.put(EmailFields.STARTTLS, starttls);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(user));
        return mimeMessage;
    }
}
