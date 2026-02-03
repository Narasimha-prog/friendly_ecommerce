package com.lnreddy.friendlyecommerce.user.application.dio;

public record AuthUserResponse(String accessToken,
                               String tokenType,
                               long expiresIn,    // duration in ms
                               long issuedAt      // creation time in epoch ms
 ) {

}