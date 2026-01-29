package com.lnreddy.friendlyecommerce.user.application.dio;

public record AuthUserRequest(
        String email,
        String password
) {
}
