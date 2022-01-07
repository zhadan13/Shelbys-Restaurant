package com.shelby.restaurant.shelbysrestaurant.service;

import com.shelby.restaurant.shelbysrestaurant.controller.resource.LoginRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {

    UserDetails login(LoginRequest request);
}
