package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import java.util.UUID;

public record ResetToken(String value) {

    public ResetToken {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Reset token must not be null or blank");
        }
    }

    public static ResetToken generate() {
        return new ResetToken(UUID.randomUUID().toString());
    }

    public static ResetToken from(String value) {
        return new ResetToken(value);
    }
}
