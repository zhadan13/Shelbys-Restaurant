package com.shelby.restaurant.shelbysrestaurant.service.authorization;

import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken getToken(String token);

    ConfirmationToken getTokenByUserId(Long userId);

    ConfirmationToken refreshToken(ConfirmationToken token);

    void setConfirmedAt(String token);
}
