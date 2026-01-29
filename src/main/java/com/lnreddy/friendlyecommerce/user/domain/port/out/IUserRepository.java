package com.lnreddy.friendlyecommerce.user.domain.port.out;

import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;

import java.util.Optional;

public interface IUserRepository {

    void save(User user);

    Optional<User> findById(UserId userId);

    Optional<User> findByEmail(Email email);

}
