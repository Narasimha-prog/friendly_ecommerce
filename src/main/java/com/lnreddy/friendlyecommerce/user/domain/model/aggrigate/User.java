package com.lnreddy.friendlyecommerce.user.domain.model.aggrigate;

import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Password;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import lombok.Getter;


public class User {

    @Getter
    private final UserId id;

    @Getter
    private Email email;

    @Getter
    private Password password;

    private static final String PASSWORD_NOT_NULL_MSG = "Password must not be null";
    private static final String EMAIL_NOT_NULL_MSG="Email must not be null";

    public User(UserId id,Email email,Password password) {
        assertNotNull(id, "UserId must not be null");
        assertNotNull(email, EMAIL_NOT_NULL_MSG);
        assertNotNull(password, PASSWORD_NOT_NULL_MSG);
        this.id = id;
        this.email=email;
        this.password=password;

    }



    public static User register(Email email, String rawPassword,IPasswordHasher passwordHasher) {
        assertNotNull(email, EMAIL_NOT_NULL_MSG);
        assertNotNull(rawPassword, PASSWORD_NOT_NULL_MSG);
        assertNotNull(passwordHasher,"PasswordHasher must not be null");
        return new User(UserId.generate(),email,Password.fromPlainText(rawPassword,passwordHasher));
    }

    public boolean validatePassword(String rawPassword, IPasswordHasher encoder) {
        assertNotNull(rawPassword, PASSWORD_NOT_NULL_MSG);
        return this.password.matches(rawPassword,encoder);
    }

    public void changeEmail(Email newEmail) {
        assertNotNull(newEmail, EMAIL_NOT_NULL_MSG);
        this.email = newEmail;
    }

    private static void assertNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
