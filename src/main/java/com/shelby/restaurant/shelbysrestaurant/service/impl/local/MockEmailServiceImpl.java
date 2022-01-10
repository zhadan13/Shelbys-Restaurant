package com.shelby.restaurant.shelbysrestaurant.service.impl.local;

import com.shelby.restaurant.shelbysrestaurant.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "mail.sender.enabled", havingValue = "false")
public class MockEmailServiceImpl implements EmailService {

    @Override
    public void send(String to, String subject, String email) {
        log.info("Sending email: Mock email sender enabled");
    }
}
