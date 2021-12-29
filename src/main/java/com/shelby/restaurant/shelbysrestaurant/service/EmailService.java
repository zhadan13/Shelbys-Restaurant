package com.shelby.restaurant.shelbysrestaurant.service;

@FunctionalInterface
public interface EmailService {

    void send(String to, String subject, String email);
}
