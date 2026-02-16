package com.lnreddy.friendlyecommerce.user.infrastracture.persistence.repository;

import com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpringDataPasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity,String> {
}
