package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class InvalidAddressException extends RuntimeException {

    private final String field;

    public InvalidAddressException(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}

