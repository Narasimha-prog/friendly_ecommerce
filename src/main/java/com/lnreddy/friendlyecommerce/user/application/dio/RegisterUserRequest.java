package com.lnreddy.friendlyecommerce.user.application.dio;

public record RegisterUserRequest(
        String name,
        String email,
        String password,
        String phoneNumber
) {}
