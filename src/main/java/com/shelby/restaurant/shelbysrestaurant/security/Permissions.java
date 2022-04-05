package com.shelby.restaurant.shelbysrestaurant.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Permissions {

    public final String GLOBAL_SCOPE = "isAuthenticated() && hasAuthority('SCOPE_global')";
}
