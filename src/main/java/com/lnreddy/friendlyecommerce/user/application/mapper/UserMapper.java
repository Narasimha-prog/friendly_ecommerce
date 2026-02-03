package com.lnreddy.friendlyecommerce.user.application.mapper;

import com.lnreddy.friendlyecommerce.user.application.dio.AuthUserRequest;
import com.lnreddy.friendlyecommerce.user.application.dio.RegisterUserRequest;
import com.lnreddy.friendlyecommerce.user.application.dio.UserView;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.*;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;

public class UserMapper {


    private UserMapper(){}

    // static mapping method
    public static UserView toUserView(User user) {
        return new UserView(
                user.getId().value().toString(),
                user.getEmail().value()
        );
    }

    public static User fromRegisterUserRequest(RegisterUserRequest registerUserRequest,
                                               IPasswordHasher iPasswordHasher) {
         return User.register(
                 new Email(registerUserRequest.email()),
                 registerUserRequest.password(),
                 iPasswordHasher,
                 new Name(registerUserRequest.name()),
                 new PhoneNumber(registerUserRequest.phoneNumber()),
                 null
         );
    }



}
