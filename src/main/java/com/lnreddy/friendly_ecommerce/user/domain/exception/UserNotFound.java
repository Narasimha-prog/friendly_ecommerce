package com.lnreddy.friendly_ecommerce.user.domain.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
