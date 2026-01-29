package com.lnreddy.friendlyecommerce.user.domain.exception;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException() {

        super("Password is very week: ");
    }
}
