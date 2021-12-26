package com.shelby.restaurant.shelbysrestaurant.service.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements Predicate<String> {
    private static final Predicate<String> IS_PHONE_NUMBER_VALID = Pattern.compile(
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", Pattern.CASE_INSENSITIVE
    ).asPredicate();

    @Override
    public boolean test(String phoneNumber) {
        return IS_PHONE_NUMBER_VALID.test(phoneNumber);
    }
}
