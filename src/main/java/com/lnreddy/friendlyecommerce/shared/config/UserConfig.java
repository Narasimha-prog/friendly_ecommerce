package com.lnreddy.friendlyecommerce.shared.config;

import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IUserRepository;
import com.lnreddy.friendlyecommerce.user.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(
            IUserRepository userRepository,
            IPasswordHasher passwordHasher
    ) {
        return new UserService(userRepository, passwordHasher);
    }
}

