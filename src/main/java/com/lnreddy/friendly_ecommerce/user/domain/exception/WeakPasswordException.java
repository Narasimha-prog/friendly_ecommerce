package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException() {

        super("Password is very week: ");
    }
}
