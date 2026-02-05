package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailIsAlreadyExisted extends UserDomainException {


    private final HttpStatus status=HttpStatus.CONFLICT;

    private final String filed="email";

    public EmailIsAlreadyExisted(String message) {

        super(message);

    }
}
