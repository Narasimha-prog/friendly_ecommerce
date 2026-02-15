package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class WeakPasswordException extends RuntimeException {



    private final HttpStatus status=HttpStatus.CONFLICT;

    private final String filed="password";

    public WeakPasswordException() {

        super("Password is very week: ");
    }


}
