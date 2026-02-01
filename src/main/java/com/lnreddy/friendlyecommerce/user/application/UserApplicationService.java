package com.lnreddy.friendlyecommerce.user.application;

import com.lnreddy.friendlyecommerce.user.application.dio.RegisterUserRequest;
import com.lnreddy.friendlyecommerce.user.application.dio.UserView;
import com.lnreddy.friendlyecommerce.user.application.mapper.UserMapper;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import com.lnreddy.friendlyecommerce.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserApplicationService {


    private final UserService userService;

    private final IPasswordHasher passwordHasher;


    @Transactional
    public UserView registerUser(RegisterUserRequest request) {
        User user = UserMapper.fromRegisterUserRequest(
                request,
                passwordHasher
        );
        return UserMapper.toUserView(user);
    }



    @Transactional(readOnly = true)
    public UserView findByUserId(UUID id){
       User user=userService.findByUserId(new UserId(id));

        return UserMapper.toUserView(user);
    }



    @Transactional(readOnly = true)
    public UserView authenticateUser(String email,String rawPassword){
       User user=  userService.validateCredentials(new Email(email),rawPassword);
        return UserMapper.toUserView(user);
    }
}
