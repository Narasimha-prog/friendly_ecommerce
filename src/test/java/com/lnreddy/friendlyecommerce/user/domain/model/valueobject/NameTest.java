package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidNameException;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Name;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void shouldCreateValidName() {
        String validName = "Alice";
        Name name = new Name(validName);
        assertEquals(validName, name.value());
    }

    @Test
    void shouldThrowExceptionForNullName() {
        InvalidNameException exception = assertThrows(
                InvalidNameException.class,
                () -> new Name(null)
        );
        assertTrue(exception.getMessage().contains("null"));
    }

    @Test
    void shouldThrowExceptionForBlankName() {
        InvalidNameException exception = assertThrows(
                InvalidNameException.class,
                () -> new Name("   ")
        );
        assertTrue(exception.getMessage().contains("blank"));
    }

    @Test
    void shouldThrowExceptionForShortName() {
        String shortName = "Al"; // assuming MIN_LENGTH = 3
        InvalidNameException exception = assertThrows(
                InvalidNameException.class,
                () -> new Name(shortName)
        );
        assertTrue(exception.getMessage().contains("at least"));
    }

    @Test
    void shouldAcceptNameExactlyAtMinLength() {
        String minLengthName = "Bob"; // assuming MIN_LENGTH = 3
        Name name = new Name(minLengthName);
        assertEquals(minLengthName, name.value());
    }
}
