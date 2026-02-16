package com.lnreddy.friendlyecommerce.user.domain.exception;

public class TokenExpiredException extends UserDomainException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
