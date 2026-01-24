package com.lnreddy.friendly_ecommerce.user.domain.model.valueobject;

import com.lnreddy.friendly_ecommerce.user.domain.exception.InvalidUserIdException;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        if (value == null) {
            throw new InvalidUserIdException();
        }
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}

