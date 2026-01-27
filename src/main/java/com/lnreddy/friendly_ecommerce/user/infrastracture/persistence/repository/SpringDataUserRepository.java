package com.lnreddy.friendly_ecommerce.user.infrastracture.persistence.repository;

import com.lnreddy.friendly_ecommerce.user.infrastracture.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
