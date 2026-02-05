package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;


import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidAddressException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void shouldCreateAddressWhenAllFieldsAreValid() {
        Address address = new Address(
                "123 Main St",
                "Hyderabad",
                "500001",
                "India"
        );

        assertNotNull(address);
        assertEquals("123 Main St", address.street());
        assertEquals("Hyderabad", address.city());
        assertEquals("500001", address.postalCode());
        assertEquals("India", address.country());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsNull() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address(null, "City", "12345", "Country")
        );

        assertEquals("Street", ex.getField());
    }

    @Test
    void shouldThrowExceptionWhenStreetIsBlank() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("   ", "City", "12345", "Country")
        );

        assertEquals("Street", ex.getField());
    }

    @Test
    void shouldThrowExceptionWhenCityIsBlank() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("Street", "", "12345", "Country")
        );

        assertEquals("City", ex.getField());
    }

    @Test
    void shouldThrowExceptionWhenCityIsNull(){

        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("Street", null, "12345", "Country")
        );

        assertEquals("City", ex.getField());

    }
    @Test
    void shouldThrowExceptionWhenPostalCodeIsBlank() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("Street", "City", " ", "Country")
        );

        assertEquals("Postal", ex.getField());
    }

    @Test
    void shouldThrowExceptionWhenPostalCodeIsNull() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("Street", "City", null, "Country")
        );

        assertEquals("Postal", ex.getField());
    }



    @Test
    void shouldThrowExceptionWhenCountryIsBlank() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("Street", "City", "12345", "")
        );

        assertEquals("Country", ex.getField());
    }
    @Test
    void shouldThrowExceptionWhenCountryIsNull() {
        InvalidAddressException ex = assertThrows(
                InvalidAddressException.class,
                () -> new Address("Street", "City", "12345", null)
        );

        assertEquals("Country", ex.getField());
    }
}

