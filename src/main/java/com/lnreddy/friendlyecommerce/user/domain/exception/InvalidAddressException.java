package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;

@Getter
public class InvalidAddressException extends RuntimeException {

    private final String field;


    public InvalidAddressException(String field) {
        this.field = field;
    }

}

