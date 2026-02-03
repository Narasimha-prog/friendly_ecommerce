package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFound extends RuntimeException {

    private final HttpStatus status;

    public UserNotFound(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

}
