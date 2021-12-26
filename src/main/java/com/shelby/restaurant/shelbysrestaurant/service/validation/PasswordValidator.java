package com.shelby.restaurant.shelbysrestaurant.service.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Predicate<String> {
    private static final Predicate<String> IS_PASSWORD_VALID = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE
    ).asPredicate();

    @Override
    public boolean test(String password) {
        return IS_PASSWORD_VALID.test(password);
    }
}
