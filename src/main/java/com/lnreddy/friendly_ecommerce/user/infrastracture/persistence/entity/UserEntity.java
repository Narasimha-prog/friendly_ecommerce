package com.lnreddy.friendly_ecommerce.user.infrastracture.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {


    @Id
    @Column(columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String hashedPassword;


}

