package com.lnreddy.friendlyecommerce.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidCredentialsException extends RuntimeException {

    private final String field;

    private final HttpStatus status = HttpStatus.UNAUTHORIZED;

    public InvalidCredentialsException(String field,String message) {
        super(message);
        this.field = field;
    }

}
