package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailIsAlreadyExisted extends RuntimeException {
    private final HttpStatus status;
    public EmailIsAlreadyExisted(String message) {

        super(message);
        this.status=HttpStatus.CONFLICT;
    }
}
