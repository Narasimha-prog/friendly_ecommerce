package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidEmailException;

public record Email(String value) {

    public Email {

        if(!value.matches("^[^@]+@[^@]+")) {
            throw new InvalidEmailException(value);
        }

    }
}
