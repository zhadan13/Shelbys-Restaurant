package com.shelby.restaurant.shelbysrestaurant.registration.service;

import com.shelby.restaurant.shelbysrestaurant.registration.resource.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest request);

    String confirmToken(String token);
}
