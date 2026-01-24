package com.lnreddy.friendly_ecommerce.user.domain.model.valueobject;


import com.lnreddy.friendly_ecommerce.user.domain.exception.InvalidNameException;

public record Name(String value) {
    public Name {
        if(value==null || value.isBlank()) throw new InvalidNameException(value);
    }
}
