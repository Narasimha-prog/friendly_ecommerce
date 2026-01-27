package com.lnreddy.friendly_ecommerce.user.infrastracture.persistence.entity;

import com.lnreddy.friendly_ecommerce.shared.audit.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractAuditingEntity<UUID> {


    @Id
    @Column(columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String hashedPassword;


}

