package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidAddressException;

public record Address(String street,
                      String city,
                      String postalCode,
                      String country) {

    public Address {
        if (street == null || street.isBlank()) {
            throw new InvalidAddressException("Street");
        }
        if (city == null || city.isBlank()) {
            throw new InvalidAddressException("City");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new InvalidAddressException("Postal");
        }
        if (country == null || country.isBlank()) {
            throw new InvalidAddressException("Country");
        }
    }
}
