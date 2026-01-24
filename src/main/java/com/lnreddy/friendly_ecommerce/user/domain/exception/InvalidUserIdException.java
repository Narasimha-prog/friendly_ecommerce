package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException() {

        super("InvalidUserId: ");
    }
}
