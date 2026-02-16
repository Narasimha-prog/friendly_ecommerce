package com.lnreddy.friendlyecommerce.user.domain.model.aggrigate;

import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.ResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PasswordResetToken {

    private final ResetToken token;
    private final UserId userId;
    private final Instant expiry;

    public PasswordResetToken(ResetToken token, UserId userId, Instant expiry) {
        this.token = token;
        this.userId = userId;
        this.expiry = expiry;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiry);
    }

    // getters...
}

