package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidEmailException;

public record Email(String value) {

    public Email {

        if(!value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new InvalidEmailException(value);
        }

    }
}
