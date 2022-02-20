package com.shelby.restaurant.shelbysrestaurant.service.authorization;

import com.shelby.restaurant.shelbysrestaurant.controller.user.resource.UserCreateRequest;

public interface RegistrationService {

    String register(UserCreateRequest request);

    String confirmToken(String token);
}
