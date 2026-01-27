package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("Invalid email or password");
    }
}
