package com.lnreddy.friendlyecommerce.user.infrastracture.persistence.repository;

import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.*;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IUserRepository;
import com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

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
    public User save(User user) {

       return  toDomain(repository.save(toEntity(user)));
    }

    // Mapping methods
    private User toDomain(UserEntity entity) {

        User user= new User(
                        new UserId(entity.getId()),
                        new Email(entity.getEmail()),
                        new Password(entity.getHashedPassword())

                        );
     if(entity.getName() !=null) user.updateName(new Name(entity.getName()));

     if (entity.getStreet() != null &&
                entity.getCity() != null &&
                entity.getPostalCode() != null &&
                entity.getCountry() != null)

            user.updateAddress(new Address(
                    entity.getStreet(),
                    entity.getCity(),
                    entity.getPostalCode(),
                    entity.getCountry()
            ));



        return user;
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().value(),
                user.getEmail().value(),
                user.getPassword().hashed() ,
                user.getRole().stream().map(Role::roleStatus).collect(Collectors.toSet()),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
