package com.lnreddy.friendlyecommerce.user.domain.model.aggrigate;

import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.*;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;


public class User {

    @Getter
    private final UserId id;

    @Getter
    private Email email;

    @Getter
    private Password password;


    private static final String EMAIL_NOT_NULL_MSG = "Email must not be null";
    @Getter
    private Set<Role> role = Set.of(new Role(UserRoleStatus.USER_ROLE));
    @Getter
    private Instant createdDate;
    @Getter
    private Instant updatedDate;
    // Future optional fields
    private Name name;
    private PhoneNumber phoneNumber;

    private static final String PASSWORD_NOT_NULL_MSG = "Password must not be null";

    private Address address;


    public User(
            UserId id,
            Email email,
            Password password) {
        assertNotNull(id, "UserId must not be null");
        assertNotNull(email, EMAIL_NOT_NULL_MSG);
        assertNotNull(password, PASSWORD_NOT_NULL_MSG);
        this.id = id;
        this.email = email;
        this.password = password;

    }

    public static User register(
            Email email,
            String rawPassword,
            IPasswordHasher passwordHasher,
            Name name,
            PhoneNumber phoneNumber,
            Address address) {
        assertNotNull(email, EMAIL_NOT_NULL_MSG);
        assertNotNull(rawPassword, PASSWORD_NOT_NULL_MSG);
        assertNotNull(
                passwordHasher,
                "PasswordHasher must not be null"
        );
        User newUser= new User(
                UserId.generate(),
                email,
                Password.fromPlainText(
                        rawPassword,
                        passwordHasher
                )
        );

           if(!(name ==null))  newUser.updateName(name);
           if(!(phoneNumber == null)) newUser.updatePhoneNumber(phoneNumber);
           if(!(address ==null)) newUser.updateAddress(address);
        return newUser;
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        assertNotNull(
                phoneNumber,
                "Phone number must not be null"
        );
        this.phoneNumber = phoneNumber;
        this.updatedDate = Instant.now();
    }

    public void updateAddress(Address address) {
        assertNotNull(
                address,
                "Address must not be null"
        );
        this.address = address;
        this.updatedDate = Instant.now();
    }

    public void updateName(Name name){
        assertNotNull(name,"Name is not be null");
        this.name=name;
    }
    public boolean validatePassword(
            String rawPassword,
            IPasswordHasher encoder) {
        assertNotNull(
                rawPassword,
                PASSWORD_NOT_NULL_MSG
        );
        return this.password.matches(
                rawPassword,
                encoder
        );
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
