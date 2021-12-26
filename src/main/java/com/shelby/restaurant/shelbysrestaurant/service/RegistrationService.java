package com.shelby.restaurant.shelbysrestaurant.service;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest request);

    String confirmToken(String token);
}
