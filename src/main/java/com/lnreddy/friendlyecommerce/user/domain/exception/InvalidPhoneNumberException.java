package com.lnreddy.friendlyecommerce.user.domain.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
