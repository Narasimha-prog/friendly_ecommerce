package com.lnreddy.friendly_ecommerce.user.domain.model.aggrigate;

import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.Password;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.UserId;
import lombok.Getter;

public class User {

    @Getter
    private final UserId id;

    private Email email;
    private Password password;

    protected User(UserId id) {
        this.id = id;
    }

    public static User register(Email email, Password password) {
        User user = new User(UserId.generate());
        user.email = email;
        user.password = password;
        return user;
    }


    public void changeEmail(Email newEmail){

        this.email=newEmail;

    }
}
