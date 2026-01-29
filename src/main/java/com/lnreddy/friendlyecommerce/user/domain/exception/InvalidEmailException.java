package com.lnreddy.friendlyecommerce.user.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String value) {

        super("Invalid email address: " + value);
    }
}
