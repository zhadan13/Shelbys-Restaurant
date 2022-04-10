package com.shelby.restaurant.shelbysrestaurant.exception.handler;

import com.shelby.restaurant.shelbysrestaurant.exception.InvalidCredentialsException;
import com.shelby.restaurant.shelbysrestaurant.exception.OrderNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.ProductNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.TokenExpiredException;
import com.shelby.restaurant.shelbysrestaurant.exception.TokenNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAccountAlreadyConfirmedException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAccountNotEnabledException;
import com.shelby.restaurant.shelbysrestaurant.exception.UserAlreadyExistsException;
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

    @ExceptionHandler(value = {ValidationException.class, TokenExpiredException.class, InvalidCredentialsException.class})
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserNotFoundException.class, TokenNotFoundException.class, ProductNotFoundException.class,
            OrderNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class, UserAccountAlreadyConfirmedException.class})
    protected ResponseEntity<Object> handleConflictException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {UserAccountNotEnabledException.class})
    protected ResponseEntity<Object> handleUnauthorizedException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
