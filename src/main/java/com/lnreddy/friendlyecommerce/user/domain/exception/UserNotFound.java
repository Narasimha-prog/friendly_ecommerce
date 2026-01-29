package com.lnreddy.friendlyecommerce.user.domain.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
