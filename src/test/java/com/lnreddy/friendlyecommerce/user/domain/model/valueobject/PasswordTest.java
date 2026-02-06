package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.WeakPasswordException;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordTest {

    @Test
    void shouldCreatePasswordFromPlainText() {
        IPasswordHasher encoder = mock(IPasswordHasher.class);

        when(encoder.hash("StrongPass")).thenReturn("hashedPass");

        Password password = Password.fromPlainText("StrongPass", encoder);

        assertEquals("hashedPass", password.hashed());
        verify(encoder, times(1)).hash("StrongPass");
    }

    @Test
    void shouldThrowExceptionForWeakPassword() {
        IPasswordHasher encoder = mock(IPasswordHasher.class);

        assertThrows(WeakPasswordException.class, () ->
                Password.fromPlainText("short", encoder));
    }

    @Test
    void shouldMatchPassword() {
        IPasswordHasher encoder = mock(IPasswordHasher.class);
        when(encoder.matches("StrongPass", "hashedPass")).thenReturn(true);

        Password password = new Password("hashedPass");
        assertTrue(password.matches("StrongPass", encoder));
    }

    @Test
    void shouldNotMatchPassword() {
        IPasswordHasher encoder = mock(IPasswordHasher.class);
        when(encoder.matches("WrongPass", "hashedPass")).thenReturn(false);

        Password password = new Password("hashedPass");
        assertFalse(password.matches("WrongPass", encoder));
    }

    @Test
    void shouldReturnFalseWhenRawPasswordIsNull() {
        IPasswordHasher encoder = mock(IPasswordHasher.class);

        Password password = new Password("hashedPass");
        assertFalse(password.matches(null, encoder));

        // matches should never be called on encoder when rawPassword is null
        verify(encoder, never()).matches(anyString(), anyString());
    }
}

