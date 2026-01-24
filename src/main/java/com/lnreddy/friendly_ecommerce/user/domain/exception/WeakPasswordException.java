package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String value) {

        super("Password is very week: "+value);
    }
}
