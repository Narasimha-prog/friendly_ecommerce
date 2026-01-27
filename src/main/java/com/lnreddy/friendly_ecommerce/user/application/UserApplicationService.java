package com.lnreddy.friendly_ecommerce.user.application;

import com.lnreddy.friendly_ecommerce.user.application.dio.RegisterUserRequest;
import com.lnreddy.friendly_ecommerce.user.application.dio.UserView;
import com.lnreddy.friendly_ecommerce.user.application.mapper.UserMapper;
import com.lnreddy.friendly_ecommerce.user.domain.model.aggrigate.User;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.Email;
import com.lnreddy.friendly_ecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendly_ecommerce.user.domain.port.out.PasswordHasher;
import com.lnreddy.friendly_ecommerce.user.domain.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserApplicationService {


    final private UserService userService;

    public UserApplicationService(UserService userService) {
        this.userService = userService;
    }


    @Transactional
    public UserView registerUser(RegisterUserRequest request) {
        User user = userService.register(
                new Email(request.email()),
                request.password()
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
