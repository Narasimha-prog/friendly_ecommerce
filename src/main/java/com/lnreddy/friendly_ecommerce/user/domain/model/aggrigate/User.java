package com.lnreddy.friendly_ecommerce.user.domain.model.aggrigate;

import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.Password;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendly_ecommerce.user.domain.port.out.PasswordHasher;
import lombok.Getter;


public class User {

    @Getter
    private final UserId id;

    @Getter
    private Email email;

    @Getter
    private Password password;

    public User(UserId id,Email email,Password password) {
        assertNotNull(id, "UserId must not be null");
        assertNotNull(email, "Email must not be null");
        assertNotNull(password, "Password must not be null");
        this.id = id;
        this.email=email;
        this.password=password;

    }



    public static User register(Email email, String rawPassword,PasswordHasher passwordHasher) {
        assertNotNull(email, "Email must not be null");
        assertNotNull(rawPassword, "Password must not be null");
        assertNotNull(passwordHasher,"PasswordHasher must not be null");
        return new User(UserId.generate(),email,Password.fromPlainText(rawPassword,passwordHasher));
    }

    public boolean validatePassword(String rawPassword, PasswordHasher encoder) {
        assertNotNull(rawPassword, "Password must not be null");
        return this.password.matches(rawPassword,encoder);
    }

    public void changeEmail(Email newEmail) {
        assertNotNull(newEmail, "New email must not be null");
        this.email = newEmail;
    }

    private static void assertNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
