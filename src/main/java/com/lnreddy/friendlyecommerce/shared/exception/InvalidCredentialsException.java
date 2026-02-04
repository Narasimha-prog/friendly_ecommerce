package com.lnreddy.friendlyecommerce.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidCredentialsException extends RuntimeException {

    private final HttpStatus status = HttpStatus.UNAUTHORIZED;

    public InvalidCredentialsException() {
        super("Email or password is incorrect");
    }

}
