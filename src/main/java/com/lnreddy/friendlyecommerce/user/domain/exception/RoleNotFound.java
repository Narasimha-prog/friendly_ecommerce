package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RoleNotFound extends UserDomainException {

    private final HttpStatus status= HttpStatus.NOT_FOUND;

    private final String field="Role";

    public RoleNotFound(String message) {

        super(message);

    }
}
