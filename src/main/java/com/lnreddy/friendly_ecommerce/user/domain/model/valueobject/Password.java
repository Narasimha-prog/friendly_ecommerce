package com.lnreddy.friendly_ecommerce.user.domain.model.valueobject;

import org.springframework.security.crypto.password.PasswordEncoder;

public record Password(String hashed) {

    public static Password fromPlainText(String raw, PasswordEncoder encoder) {
        if (raw.length() < 8)  throw new WeakPasswordException();

        return new Password(encoder.encode(raw));
    }
}

