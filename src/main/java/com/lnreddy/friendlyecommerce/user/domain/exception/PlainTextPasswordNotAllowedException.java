package com.lnreddy.friendlyecommerce.user.domain.exception;

public class PlainTextPasswordNotAllowedException extends UserDomainException {
    public PlainTextPasswordNotAllowedException(String message) {
        super(message);
    }
}
