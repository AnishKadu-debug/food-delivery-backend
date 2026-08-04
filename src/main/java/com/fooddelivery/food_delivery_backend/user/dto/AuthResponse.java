package com.fooddelivery.food_delivery_backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Authentication response returned after successful login.")
public class AuthResponse {

    @Schema(
            description = "JWT access token used to authenticate future requests."
    )
    private String token;

    @Schema(
            description = "Authenticated user's profile information."
    )
    private UserResponse user;
}