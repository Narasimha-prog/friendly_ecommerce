package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends RuntimeException {

    private final HttpStatus status;

    private final String field="email";

    public InvalidEmailException(String value) {

        super("Invalid email address: " + value);
        this.status=HttpStatus.BAD_REQUEST;
    }
}
