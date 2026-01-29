package com.lnreddy.friendlyecommerce.user.application.dio;

public record RegisterUserRequest(
        String email,
        String password
) {}
