package com.lnreddy.friendlyecommerce.user.infrastracture.persistence.repository;

import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Password;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IUserRepository;
import com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements IUserRepository {

    private final SpringDataUserRepository repository;


    public UserRepositoryAdapter(SpringDataUserRepository repository) {
        this.repository = repository;

    }

    @Override
    public Optional<User> findById(UserId id) {
        return repository.findById(id.value())
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return repository.findByEmail(email.value())
                .map(this::toDomain);
    }

    @Override
    public void save(User user) {
        repository.save(toEntity(user));
    }

    // Mapping methods
    private User toDomain(UserEntity entity) {

        return new User(
                        new UserId(entity.getId()),
                        new Email(entity.getEmail()),
                        new Password(entity.getHashedPassword())

                        );
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().value(),
                user.getEmail().value(),
                user.getPassword().hashed() // add getter in domain for persistence only
        );
    }
}
