package com.lnreddy.friendlyecommerce.user.infrastracture.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "password_reset_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordResetTokenEntity {

    @Id
    private String token;

    private UUID userId;

    private Instant expiryDate;
}
