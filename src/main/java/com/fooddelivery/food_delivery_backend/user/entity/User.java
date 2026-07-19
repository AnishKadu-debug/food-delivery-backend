package com.fooddelivery.food_delivery_backend.user.entity;


import com.fooddelivery.food_delivery_backend.common.audit.BaseAuditEntity;
import com.fooddelivery.food_delivery_backend.user.enums.AuthProvider;
import com.fooddelivery.food_delivery_backend.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class User extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean emailVerified;

}
