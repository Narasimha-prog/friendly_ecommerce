package com.lnreddy.friendlyecommerce.user.application.mapper;

import com.lnreddy.friendlyecommerce.user.domain.model.aggrigate.PasswordResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.ResetToken;
import com.lnreddy.friendlyecommerce.user.domain.model.valueobject.UserId;
import com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity.PasswordResetTokenEntity;

public class PasswordResetTokenMapper {

    private PasswordResetTokenMapper() {
    }


    public static PasswordResetTokenEntity toEntity(PasswordResetToken token) {
        PasswordResetTokenEntity entity = new PasswordResetTokenEntity();
        entity.setToken(token.getToken().value());
        entity.setUserId(token.getUserId().value());
        entity.setExpiryDate(token.getExpiry());
        return entity;
    }

    public static PasswordResetToken toDomain(PasswordResetTokenEntity entity) {
        return new PasswordResetToken(
                new ResetToken(entity.getToken()),
                new UserId(entity.getUserId()),
                entity.getExpiryDate()
        );
    }
}
