package com.fooddelivery.food_delivery_backend.restaurant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "Request payload for updating an existing restaurant. Only the fields that need to be changed should be provided.")
public class UpdateRestaurantRequest {

    @Schema(
            description = "Updated restaurant name.",
            example = "Spice Garden Premium"
    )
    private String name;

    @Schema(
            description = "Updated restaurant description.",
            example = "Authentic North Indian, Chinese, and Continental cuisine."
    )
    private String description;

    @Schema(
            description = "Updated restaurant contact number.",
            example = "9876543210"
    )
    private String phone;

    @Schema(
            description = "Updated restaurant email address.",
            example = "contact@spicegarden.com"
    )
    private String email;

    @Schema(
            description = "Updated street address.",
            example = "456 FC Road"
    )
    private String address;

    @Schema(
            description = "Updated city.",
            example = "Pune"
    )
    private String city;

    @Schema(
            description = "Updated state.",
            example = "Maharashtra"
    )
    private String state;

    @Schema(
            description = "Updated postal PIN code.",
            example = "411005"
    )
    private String pincode;

    @Schema(
            description = "Updated latitude coordinate.",
            example = "18.5204"
    )
    private Double latitude;

    @Schema(
            description = "Updated longitude coordinate.",
            example = "73.8567"
    )
    private Double longitude;

    @Schema(
            description = "Updated daily opening time.",
            example = "10:00:00"
    )
    private LocalTime openingTime;

    @Schema(
            description = "Updated daily closing time.",
            example = "23:30:00"
    )
    private LocalTime closingTime;

    @Schema(
            description = "Whether the restaurant is currently open for accepting orders.",
            example = "true"
    )
    private Boolean open;
}