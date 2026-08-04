package com.fooddelivery.food_delivery_backend.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating a new delivery address.")
public class CreateAddressRequest {

    @Schema(
            description = "Recipient's full name.",
            example = "Rahul Sharma"
    )
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Schema(
            description = "Recipient's mobile number.",
            example = "9876543210"
    )
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number"
    )
    private String phone;

    @Schema(
            description = "Primary address line.",
            example = "Flat 302, ABC Residency"
    )
    @NotBlank(message = "Address Line 1 is required")
    private String addressLine1;

    @Schema(
            description = "Secondary address line.",
            example = "Near City Mall"
    )
    private String addressLine2;

    @Schema(
            description = "City.",
            example = "Pune"
    )
    @NotBlank(message = "City is required")
    private String city;

    @Schema(
            description = "State.",
            example = "Maharashtra"
    )
    @NotBlank(message = "State is required")
    private String state;

    @Schema(
            description = "Postal PIN code.",
            example = "411001"
    )
    @NotBlank(message = "Pincode is required")
    @Pattern(
            regexp = "^\\d{6}$",
            message = "Invalid pincode"
    )
    private String pincode;

    @Schema(
            description = "Nearby landmark.",
            example = "Opposite Metro Station"
    )
    private String landmark;
}