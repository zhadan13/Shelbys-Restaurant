package com.shelby.restaurant.shelbysrestaurant.service.authorization;

import com.shelby.restaurant.shelbysrestaurant.controller.authorization.resource.LoginRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {

    UserDetails login(LoginRequest request);
}
