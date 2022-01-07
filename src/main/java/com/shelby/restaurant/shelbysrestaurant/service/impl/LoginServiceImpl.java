package com.shelby.restaurant.shelbysrestaurant.service.impl;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.LoginRequest;
import com.shelby.restaurant.shelbysrestaurant.exception.InvalidCredentialsException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAccountNotEnabledException;
import com.shelby.restaurant.shelbysrestaurant.service.LoginService;
import com.shelby.restaurant.shelbysrestaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    @Override
    public UserDetails login(LoginRequest request) {
        log.info("Logging user");
        UserDetails user = userService.loadUserByUsername(request.getUsername());
        if (!userService.comparePasswords(request.getPassword(), user.getPassword())) {
            log.error("Passed user password not correct");
            throw new InvalidCredentialsException("Passed user password not correct!");
        }
        if (!user.isEnabled()) {
            log.error("User account not enabled");
            throw new UserAccountNotEnabledException("User account not enabled!");
        }
        return user;
    }
}
