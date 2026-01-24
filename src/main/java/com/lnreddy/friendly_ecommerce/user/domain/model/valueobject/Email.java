package com.lnreddy.friendly_ecommerce.user.domain.model.valueobject;

import com.lnreddy.friendly_ecommerce.user.domain.exception.InvalidEmailException;

public record Email(String value) {

    public Email {

        if(!value.matches("^[^@]+@[^@]+")) {
            throw new InvalidEmailException(value);
        }

    }
}
