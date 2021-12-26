package com.shelby.restaurant.shelbysrestaurant.controller.resource;

import lombok.Value;

@Value
public class RegistrationRequest {

    String email;
    String phoneNumber;
    String password;
    String firstName;
    String lastName;
}
