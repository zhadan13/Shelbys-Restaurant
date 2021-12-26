package com.shelby.restaurant.shelbysrestaurant.service.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class CardExpirationDateValidator implements Predicate<String> {
    private static final Predicate<String> IS_CARD_EXPIRATION_DATE_VALID = Pattern.compile(
            "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$", Pattern.CASE_INSENSITIVE
    ).asPredicate();

    @Override
    public boolean test(String date) {
        return IS_CARD_EXPIRATION_DATE_VALID.test(date);
    }
}
