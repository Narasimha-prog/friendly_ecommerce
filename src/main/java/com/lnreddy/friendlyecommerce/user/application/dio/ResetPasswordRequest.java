package com.lnreddy.friendlyecommerce.user.application.dio;
public record ResetPasswordRequest(
        String token,
        String newPassword
) {}
