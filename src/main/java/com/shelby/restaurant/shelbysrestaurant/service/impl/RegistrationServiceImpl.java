package com.shelby.restaurant.shelbysrestaurant.service.impl;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.exception.TokenExpiredException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAccountAlreadyConfirmedException;
import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import com.shelby.restaurant.shelbysrestaurant.service.ConfirmationTokenService;
import com.shelby.restaurant.shelbysrestaurant.service.EmailService;
import com.shelby.restaurant.shelbysrestaurant.service.RegistrationService;
import com.shelby.restaurant.shelbysrestaurant.service.UserService;
import com.shelby.restaurant.shelbysrestaurant.util.EmailTemplates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private static final String CONFIRM_TOKEN_PATH = "/registration/confirm?token=";

    @Value("${application.path}")
    private String applicationPath;

    @Value("${confirmation.token.expiration.time}")
    private Integer time;

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    @Override
    @Transactional
    public String register(UserCreateRequest request) {
        log.info("Registering new user");
        User user = userService.createUser(request);
        final String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(time))
                .user(user)
                .build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        final String link = applicationPath + CONFIRM_TOKEN_PATH + token;
        emailService.send(user.getEmail(), EmailTemplates.CONFIRM_EMAIL_SUBJECT,
                EmailTemplates.buildEmail(user.getFirstName(), link));
        return token;
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            log.info("User account already confirmed");
            throw new UserAccountAlreadyConfirmedException("User account already confirmed!");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            log.error("Token expired");
            throw new TokenExpiredException("Token expired!");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
