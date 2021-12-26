package com.shelby.restaurant.shelbysrestaurant.service;

import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    void setConfirmedAt(String token);
}
