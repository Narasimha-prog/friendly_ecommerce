package com.lnreddy.friendlyecommerce.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidAddressException extends UserDomainException {

    private final HttpStatus  status= HttpStatus.BAD_REQUEST;

    private final String field;

    public InvalidAddressException(String field) {
        super(getUserMessage(field));

        this.field = field;
    }

    private static String getUserMessage(String field) {
        return switch (field) {
            case "Street" -> "Street cannot be empty";
            case "City" -> "City cannot be empty";
            case "Postal" -> "Postal code cannot be empty";
            case "Country" -> "Country cannot be empty";
            default -> "Invalid value for " + field;
        };
    }

}

