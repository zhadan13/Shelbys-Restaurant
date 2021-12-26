package com.shelby.restaurant.shelbysrestaurant.service.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class CVVValidator implements Predicate<String> {
    private static final Predicate<String> IS_CVV_VALID = Pattern.compile(
            "^[0-9]{3}$", Pattern.CASE_INSENSITIVE
    ).asPredicate();

    @Override
    public boolean test(String cvv) {
        return IS_CVV_VALID.test(cvv);
    }
}
