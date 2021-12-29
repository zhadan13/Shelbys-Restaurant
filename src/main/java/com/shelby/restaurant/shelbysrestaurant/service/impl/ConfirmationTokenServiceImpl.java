package com.shelby.restaurant.shelbysrestaurant.service.impl;

import com.shelby.restaurant.shelbysrestaurant.exception.TokenNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
import com.shelby.restaurant.shelbysrestaurant.repository.token.ConfirmationTokenRepository;
import com.shelby.restaurant.shelbysrestaurant.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        log.info("Saving confirmation token");
        confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmationToken getToken(String token) {
        log.info("Getting confirmation token");
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Token not found!"));
    }

    @Override
    public void setConfirmedAt(String token) {
        log.info("Updating confirmation token status");
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
