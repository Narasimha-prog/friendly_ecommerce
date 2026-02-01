package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public record PhoneNumber(String value) {

    // E.164 format (recommended for ecommerce & global support)
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[1-9]\\d{7,14}$");

    public PhoneNumber {
        if (value == null || value.isBlank()) {
            throw new InvalidPhoneNumberException("Phone number cannot be null or blank");
        }

        String normalized = value.replaceAll("\\s+", "");

        if (!PHONE_PATTERN.matcher(normalized).matches()) {
            throw new InvalidPhoneNumberException("Invalid phone number format: " + value);
        }
    }

    public String normalized() {
        return value.replaceAll("\\s+", "");
    }
}
