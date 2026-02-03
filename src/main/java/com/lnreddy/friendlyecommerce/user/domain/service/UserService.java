package com.lnreddy.friendlyecommerce.user.domain.service;

import com.lnreddy.friendlyecommerce.user.application.mapper.UserMapper;
import com.lnreddy.friendlyecommerce.user.domain.exception.UserNotFound;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IUserRepository;

import java.util.UUID;

public class UserService {

    private final IUserRepository userRepository;
    private final IPasswordHasher passwordHasher;

    public UserService(
            IUserRepository userRepository,
            IPasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    // ✅ registration coordination
    public User register(User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already registered");
        }




        return  userRepository.save(newUser);
    }

    // ✅ credential validation coordination
    public User validateCredentials(Email email, String rawPassword) {


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.validatePassword(rawPassword, passwordHasher)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return user;
    }


    public User findByUserId(UserId userId){
     return     userRepository.findById(userId).orElseThrow(
                  ()-> new UserNotFound("There is no user with this id: "+userId.value())
          );
    }

    public User findByUserEmailId(Email email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFound("There is no user with this email id : "+email.value())
        );
    }



}
