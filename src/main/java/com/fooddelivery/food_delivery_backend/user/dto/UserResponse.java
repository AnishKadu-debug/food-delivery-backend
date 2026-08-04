package com.fooddelivery.food_delivery_backend.user.dto;

import com.fooddelivery.food_delivery_backend.user.enums.AuthProvider;
import com.fooddelivery.food_delivery_backend.user.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a user returned by the API.")
public class UserResponse {

    @Schema(
            description = "Unique identifier of the user.",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Full name of the user.",
            example = "Rahul Sharma"
    )
    private String name;

    @Schema(
            description = "Registered email address.",
            example = "rahul@example.com"
    )
    private String email;

    @Schema(
            description = "Registered mobile number.",
            example = "9876543210"
    )
    private String phone;

    @Schema(
            description = "Role assigned to the user.",
            example = "CUSTOMER"
    )
    private Role role;

    @Schema(
            description = "Whether the user account is active.",
            example = "true"
    )
    private boolean active;

    @Schema(
            description = "Authentication provider used to create the account.",
            example = "LOCAL"
    )
    private AuthProvider provider;

    @Schema(
            description = "Whether the user's email address has been verified.",
            example = "true"
    )
    private boolean emailVerified;
}