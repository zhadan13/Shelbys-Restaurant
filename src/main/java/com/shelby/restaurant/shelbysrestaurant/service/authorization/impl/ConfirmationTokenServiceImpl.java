package com.shelby.restaurant.shelbysrestaurant.service.authorization.impl;

import com.shelby.restaurant.shelbysrestaurant.exception.TokenNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
import com.shelby.restaurant.shelbysrestaurant.repository.token.ConfirmationTokenRepository;
import com.shelby.restaurant.shelbysrestaurant.service.authorization.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Value("${confirmation.token.expiration.time}")
    private Integer tokenExpirationTime;

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
    public ConfirmationToken getTokenByUserId(String userId) {
        log.info("Getting confirmation token by userId");
        return confirmationTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new TokenNotFoundException("Token not found!"));
    }

    @Override
    public ConfirmationToken refreshToken(ConfirmationToken token) {
        log.info("Refreshing confirmation token");
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(tokenExpirationTime));
        return confirmationTokenRepository.save(token);
    }

    @Override
    public void setConfirmedAt(String token) {
        log.info("Updating confirmation token status");
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public void deleteConfirmationToken(String id) {
        log.info("Deleting confirmation token with id {}", id);
        confirmationTokenRepository.findById(id).ifPresentOrElse(confirmationTokenRepository::delete, () -> {
            log.error("Token with id {} not found", id);
            throw new TokenNotFoundException("Token with id " + id + " not found!");
        });
    }

    @Override
    public void deleteConfirmationTokenByUserId(String userId) {
        log.info("Deleting confirmation token by userId {}", userId);
        confirmationTokenRepository.findByUserId(userId).ifPresentOrElse(confirmationTokenRepository::delete, () -> {
            log.error("Token with userid {} not found", userId);
            throw new TokenNotFoundException("Token with userId " + userId + " not found!");
        });
    }
}
