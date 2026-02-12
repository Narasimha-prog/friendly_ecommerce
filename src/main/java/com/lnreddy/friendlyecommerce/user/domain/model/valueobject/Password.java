package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.PlainTextPasswordNotAllowedException;
import com.lnreddy.friendlyecommerce.user.domain.exception.WeakPasswordException;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;


public record Password(String hashed) {

    public Password(String hashed) {

        if (hashed == null || hashed.isBlank()) {
            throw new PlainTextPasswordNotAllowedException("");
        }

        this.hashed = hashed;
    }

    public static Password fromPlainText(String raw, IPasswordHasher encoder) {

        if (raw.length() < 8)  throw new WeakPasswordException();

        return new Password(encoder.hash(raw));
    }

    public boolean matches(String rawPassword, IPasswordHasher encoder) {
        if (rawPassword == null) {
            return false;
        }
        return encoder.matches(rawPassword, this.hashed);
    }
}

