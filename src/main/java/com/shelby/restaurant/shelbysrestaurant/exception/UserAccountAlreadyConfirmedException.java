package com.shelby.restaurant.shelbysrestaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAccountAlreadyConfirmedException extends RuntimeException {

    public UserAccountAlreadyConfirmedException() {
    }

    public UserAccountAlreadyConfirmedException(String message) {
        super(message);
    }

    public UserAccountAlreadyConfirmedException(String message, Exception e) {
        super(message, e);
    }
}
