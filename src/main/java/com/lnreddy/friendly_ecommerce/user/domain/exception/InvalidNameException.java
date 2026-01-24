package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String value) {
        super("Invalid Name: "+value);
    }
}
