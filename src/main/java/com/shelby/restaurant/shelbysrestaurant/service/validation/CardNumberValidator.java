package com.shelby.restaurant.shelbysrestaurant.service.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class CardNumberValidator implements Predicate<String> {
    private static final Predicate<String> IS_CARD_NUMBER_VALID = Pattern.compile(
            "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$", Pattern.CASE_INSENSITIVE
    ).asPredicate();

    @Override
    public boolean test(String cardNumber) {
        return IS_CARD_NUMBER_VALID.test(cardNumber);
    }
}
