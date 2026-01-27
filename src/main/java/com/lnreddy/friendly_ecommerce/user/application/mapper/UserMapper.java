package com.lnreddy.friendly_ecommerce.user.application.mapper;

import com.lnreddy.friendly_ecommerce.user.application.dio.UserView;
import com.lnreddy.friendly_ecommerce.user.domain.model.aggrigate.User;

public class UserMapper {

    // static mapping method
    public static UserView toUserView(User user) {
        return new UserView(
                user.getId().value().toString(),
                user.getEmail().value()
        );
    }
}
