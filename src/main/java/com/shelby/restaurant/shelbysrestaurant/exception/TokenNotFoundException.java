package com.shelby.restaurant.shelbysrestaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException() {
    }

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
