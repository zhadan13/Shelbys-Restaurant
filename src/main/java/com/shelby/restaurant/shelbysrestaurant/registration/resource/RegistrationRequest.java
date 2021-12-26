package com.shelby.restaurant.shelbysrestaurant.registration.resource;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final String firstName;
    private final String lastName;
}
