package com.shelby.restaurant.shelbysrestaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserAccountNotEnabledException extends RuntimeException {

    public UserAccountNotEnabledException() {
    }

    public UserAccountNotEnabledException(String message) {
        super(message);
    }

    public UserAccountNotEnabledException(String message, Exception e) {
        super(message, e);
    }
}

