package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;


import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidNameException;

public record Name(String value) {

    private static final int MIN_LENGTH = 3;

    public Name {
        if (value == null || value.isBlank()) {
            throw new InvalidNameException("Name cannot be null or blank: " + value);
        }
        if (value.length() < MIN_LENGTH) {
            throw new InvalidNameException("Name must be at least " + MIN_LENGTH + " characters: " + value);
        }


    }
}
