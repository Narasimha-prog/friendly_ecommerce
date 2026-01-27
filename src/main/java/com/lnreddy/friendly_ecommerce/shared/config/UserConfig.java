package com.lnreddy.friendly_ecommerce.shared.config;

import com.lnreddy.friendly_ecommerce.user.domain.port.out.PasswordHasher;
import com.lnreddy.friendly_ecommerce.user.domain.port.out.UserRepository;
import com.lnreddy.friendly_ecommerce.user.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(
            UserRepository userRepository,
            PasswordHasher passwordHasher
    ) {
        return new UserService(userRepository, passwordHasher);
    }
}

