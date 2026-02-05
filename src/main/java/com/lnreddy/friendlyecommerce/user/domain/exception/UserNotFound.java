package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFound extends UserDomainException {

    private final HttpStatus status= HttpStatus.NOT_FOUND;

    private final String field="User";

    public UserNotFound(String message) {
        super(message);

    }

}
