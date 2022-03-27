package com.shelby.restaurant.shelbysrestaurant.service.authorization.impl;

import com.shelby.restaurant.shelbysrestaurant.controller.authorization.resource.LoginRequest;
import com.shelby.restaurant.shelbysrestaurant.exception.InvalidCredentialsException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAccountNotEnabledException;
import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import com.shelby.restaurant.shelbysrestaurant.service.authorization.ConfirmationTokenService;
import com.shelby.restaurant.shelbysrestaurant.service.authorization.LoginService;
import com.shelby.restaurant.shelbysrestaurant.service.email.EmailService;
import com.shelby.restaurant.shelbysrestaurant.service.user.UserService;
import com.shelby.restaurant.shelbysrestaurant.util.EmailTemplates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private static final String CONFIRM_TOKEN_PATH = "/registration/confirm?token=";

    @Value("${application.path}")
    private String applicationPath;

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    @Override
    public UserDetails login(LoginRequest request) {
        log.info("Logging user");
        User user = (User) userService.loadUserByUsername(request.getUsername());
        if (!userService.comparePasswords(request.getPassword(), user.getPassword())) {
            log.error("Passed user password not correct");
            throw new InvalidCredentialsException("Passed user password not correct!");
        }
        if (!user.isEnabled()) {
            log.info("User account not enabled");
            ConfirmationToken token = confirmationTokenService.getTokenByUserId(user.getId());
            if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
                ConfirmationToken newToken = confirmationTokenService.refreshToken(token);
                final String link = applicationPath + CONFIRM_TOKEN_PATH + newToken;
                emailService.send(user.getEmail(), EmailTemplates.CONFIRM_EMAIL_SUBJECT,
                        EmailTemplates.buildConfirmEmail(user.getFirstName(), link));
            }
            throw new UserAccountNotEnabledException("User account not enabled!");
        }
        return user;
    }
}
