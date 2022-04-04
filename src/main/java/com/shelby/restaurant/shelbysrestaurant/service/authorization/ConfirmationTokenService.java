package com.shelby.restaurant.shelbysrestaurant.service.authorization;

import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken getToken(String token);

    ConfirmationToken getTokenByUserId(String userId);

    ConfirmationToken refreshToken(ConfirmationToken token);

    void setConfirmedAt(String token);

    void deleteConfirmationToken(String id);

    void deleteConfirmationTokenByUserId(String userId);
}
