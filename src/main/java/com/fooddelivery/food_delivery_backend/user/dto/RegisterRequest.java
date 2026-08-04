package com.fooddelivery.food_delivery_backend.user.dto;

import com.fooddelivery.food_delivery_backend.user.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for registering a new user.")
public class RegisterRequest {

    @Schema(
            description = "Full name of the user.",
            example = "Rahul Sharma"
    )
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @Schema(
            description = "Unique email address used for login.",
            example = "rahul@example.com"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Schema(
            description = "Password containing at least 8 characters.",
            example = "Password@123"
    )
    @NotBlank(message = "Password is required")
    @Size(min = 8)
    private String password;

    @Schema(
            description = "10-digit Indian mobile number.",
            example = "9876543210"
    )
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number"
    )
    private String phone;

    @Schema(
            description = "Role assigned to the user.",
            example = "CUSTOMER"
    )
    private Role role;
}