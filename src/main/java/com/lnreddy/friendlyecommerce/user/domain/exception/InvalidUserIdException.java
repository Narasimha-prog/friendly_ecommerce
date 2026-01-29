package com.lnreddy.friendlyecommerce.user.domain.exception;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException() {

        super("InvalidUserId: ");
    }
}
