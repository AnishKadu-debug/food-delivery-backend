package com.fooddelivery.food_delivery_backend.address.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a customer delivery address returned by the API.")
public class AddressResponse {

    @Schema(
            description = "Unique identifier of the address.",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Recipient's full name.",
            example = "Rahul Sharma"
    )
    private String fullName;

    @Schema(
            description = "Recipient's mobile number.",
            example = "9876543210"
    )
    private String phone;

    @Schema(
            description = "Primary address line.",
            example = "Flat 302, ABC Residency"
    )
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
    private String city;

    @Schema(
            description = "State.",
            example = "Maharashtra"
    )
    private String state;

    @Schema(
            description = "Postal PIN code.",
            example = "411001"
    )
    private String pincode;

    @Schema(
            description = "Nearby landmark.",
            example = "Opposite Metro Station"
    )
    private String landmark;

    @Schema(
            description = "Indicates whether this is the customer's default delivery address.",
            example = "true"
    )
    private boolean isDefault;
}