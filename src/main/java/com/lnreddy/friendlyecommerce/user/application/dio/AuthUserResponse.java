package com.lnreddy.friendlyecommerce.user.application.dio;

public record AuthUserResponse(String accessToken,
                               String tokenType,
                               long expiresIn) {
}
