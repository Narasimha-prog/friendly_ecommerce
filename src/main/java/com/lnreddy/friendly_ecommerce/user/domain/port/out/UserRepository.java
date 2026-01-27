package com.lnreddy.friendly_ecommerce.user.domain.port.out;

import com.lnreddy.friendly_ecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.UserId;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(UserId userId);

    Optional<User> findByEmail(Email email);

}
