package com.lnreddy.friendlyecommerce.user.infrastracture.persistence.repository;

import com.lnreddy.friendlyecommerce.user.application.mapper.PasswordResetTokenMapper;
import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.PasswordResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.ResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.domain.port.out.IPasswordResetTokenRepository;
import com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity.PasswordResetTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordResetTokenRepositoryImp implements IPasswordResetTokenRepository {


    private final ISpringDataPasswordResetTokenRepository resetTokenRepository;

    @Override
    public void save(ResetToken token, UserId userId, Instant expiry) {
        resetTokenRepository.save(
                new PasswordResetTokenEntity(token.value(), userId.value(), expiry)
        );
    }

    @Override
    public Optional<PasswordResetToken> findPasswordResetTokenByToken(ResetToken token) {
        return resetTokenRepository.findById(token.value())
                .filter(t -> t.getExpiryDate().isAfter(Instant.now()))
                .map(PasswordResetTokenMapper::toDomain);
    }

    @Override
    public void deleteByToken(ResetToken token) {
        resetTokenRepository.deleteById(token.value());
    }
}
