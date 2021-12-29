package com.shelby.restaurant.shelbysrestaurant.service;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.UserCreateRequest;

public interface RegistrationService {

    String register(UserCreateRequest request);

    String confirmToken(String token);
}
