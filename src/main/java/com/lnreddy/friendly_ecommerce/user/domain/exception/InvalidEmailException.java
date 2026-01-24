package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String value) {

        super("Invalid email address: " + value);
    }
}
