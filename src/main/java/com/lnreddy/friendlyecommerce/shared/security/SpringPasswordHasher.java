package com.lnreddy.friendlyecommerce.shared.security;

import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringPasswordHasher implements IPasswordHasher {


    private final PasswordEncoder encoder;


    @Override
    public String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
