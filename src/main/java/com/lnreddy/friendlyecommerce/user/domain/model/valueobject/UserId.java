package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidUserIdException;

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

