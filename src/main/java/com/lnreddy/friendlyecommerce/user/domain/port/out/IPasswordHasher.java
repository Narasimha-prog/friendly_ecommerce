package com.lnreddy.friendlyecommerce.user.domain.port.out;

public interface IPasswordHasher {

    String hash(String rawPassword);

    boolean matches(String rawPassword, String hashedPassword);
}
