package com.lnreddy.friendlyecommerce.user.domain.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("Invalid email or password");
    }
}
