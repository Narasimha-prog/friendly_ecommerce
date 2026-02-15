package com.lnreddy.friendlyecommerce.user.domain.service;

import com.lnreddy.friendlyecommerce.shared.exception.InvalidCredentialsException;
import com.lnreddy.friendlyecommerce.user.domain.exception.EmailIsAlreadyExisted;
import com.lnreddy.friendlyecommerce.user.domain.exception.UserNotFound;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IUserRepository;

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
            throw new EmailIsAlreadyExisted("Email already registered");
        }




        return  userRepository.save(newUser);
    }

    // ✅ credential validation coordination
    public User validateCredentials(Email email, String rawPassword) {


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("email","Email is incorrect"));

        if (!user.validatePassword(rawPassword, passwordHasher)) {
            throw new InvalidCredentialsException("password","password is incorrect");
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
