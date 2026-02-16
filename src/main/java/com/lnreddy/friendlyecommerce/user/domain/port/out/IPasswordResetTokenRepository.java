package com.lnreddy.friendlyecommerce.user.domain.port.out;

import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.PasswordResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.ResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;

import java.time.Instant;
import java.util.Optional;

public interface IPasswordResetTokenRepository {

    void save(ResetToken token, UserId userId, Instant expiry);

    Optional<PasswordResetToken> findPasswordResetTokenByToken(ResetToken token);

    void deleteByToken(ResetToken token);
}
