package com.shelby.restaurant.shelbysrestaurant.registration.token.service;

import com.shelby.restaurant.shelbysrestaurant.registration.token.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    void setConfirmedAt(String token);
}
