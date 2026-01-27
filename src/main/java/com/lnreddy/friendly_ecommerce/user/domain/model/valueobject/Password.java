package com.lnreddy.friendly_ecommerce.user.domain.model.valueobject;

import com.lnreddy.friendly_ecommerce.user.domain.exception.WeakPasswordException;
import com.lnreddy.friendly_ecommerce.user.domain.port.out.PasswordHasher;


public record Password(String hashed) {

    public static Password fromPlainText(String raw, PasswordHasher encoder) {
        if (raw.length() < 8)  throw new WeakPasswordException();

        return new Password(encoder.hash(raw));
    }

    public boolean matches(String rawPassword, PasswordHasher encoder) {
        if (rawPassword == null) {
            return false;
        }
        return encoder.matches(rawPassword, this.hashed);
    }
}

