package com.lnreddy.friendly_ecommerce.user.application.dio;

public record RegisterUserRequest(
        String email,
        String password
) {}
