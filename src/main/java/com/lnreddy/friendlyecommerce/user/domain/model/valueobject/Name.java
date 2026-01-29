package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;


import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidNameException;

public record Name(String value) {
    public Name {
        if(value==null || value.isBlank()) throw new InvalidNameException(value);
    }
}
