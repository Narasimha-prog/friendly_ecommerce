package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        Email email = new Email("test@example.com");

        assertEquals("test@example.com", email.value());
    }

    @Test
    void shouldThrowExceptionForInvalidEmail() {

        assertThrows(InvalidEmailException.class, () -> new Email("invalid-email"));
    }
    @Test
    void shouldThrowExceptionForNull(){

        assertThrows(InvalidEmailException.class,()-> new Email(null));
    }
}