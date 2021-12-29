package com.shelby.restaurant.shelbysrestaurant.exception.handler;

import com.shelby.restaurant.shelbysrestaurant.exception.TokenExpiredException;
import com.shelby.restaurant.shelbysrestaurant.exception.TokenNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAccountAlreadyConfirmedException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAlreadyExists;
import com.shelby.restaurant.shelbysrestaurant.exception.UserNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, ValidationException.class, TokenExpiredException.class,
            TokenNotFoundException.class})
    protected ResponseEntity<?> handleBadRequestException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserAlreadyExists.class, UserAccountAlreadyConfirmedException.class})
    protected ResponseEntity<?> handleConflictException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
