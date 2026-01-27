package com.lnreddy.friendly_ecommerce.user.domain.port.out;

public interface PasswordHasher {

    String hash(String rawPassword);

    boolean matches(String rawPassword, String hashedPassword);
}
