package com.fooddelivery.food_delivery_backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for user authentication.")
public class LoginRequest {

    @Schema(
            description = "Registered email address.",
            example = "rahul@example.com"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @Schema(
            description = "User password.",
            example = "Password@123"
    )
    @NotBlank(message = "Password is required")
    private String password;
}