package com.lnreddy.friendlyecommerce.user.application.mapper;

import com.lnreddy.friendlyecommerce.user.application.dio.UserView;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;

public class UserMapper {


    private UserMapper(){}

    // static mapping method
    public static UserView toUserView(User user) {
        return new UserView(
                user.getId().value().toString(),
                user.getEmail().value()
        );
    }
}
