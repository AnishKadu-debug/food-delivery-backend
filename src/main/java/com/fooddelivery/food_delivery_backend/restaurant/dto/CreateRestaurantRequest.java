package com.fooddelivery.food_delivery_backend.restaurant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "Request payload for creating a new restaurant.")
public class CreateRestaurantRequest {

    @Schema(
            description = "Name of the restaurant.",
            example = "Spice Garden"
    )
    @NotBlank
    private String name;

    @Schema(
            description = "Short description of the restaurant.",
            example = "Authentic North Indian and Chinese cuisine."
    )
    private String description;

    @Schema(
            description = "Restaurant contact number.",
            example = "9876543210"
    )
    @NotBlank
    private String phone;

    @Schema(
            description = "Restaurant contact email address.",
            example = "contact@spicegarden.com"
    )
    @Email
    private String email;

    @Schema(
            description = "Street address of the restaurant.",
            example = "123 MG Road"
    )
    @NotBlank
    private String address;

    @Schema(
            description = "City where the restaurant is located.",
            example = "Pune"
    )
    @NotBlank
    private String city;

    @Schema(
            description = "State where the restaurant is located.",
            example = "Maharashtra"
    )
    @NotBlank
    private String state;

    @Schema(
            description = "Postal PIN code.",
            example = "411001"
    )
    @NotBlank
    private String pincode;

    @Schema(
            description = "Latitude coordinate of the restaurant location.",
            example = "18.5204"
    )
    private Double latitude;

    @Schema(
            description = "Longitude coordinate of the restaurant location.",
            example = "73.8567"
    )
    private Double longitude;

    @Schema(
            description = "Daily opening time.",
            example = "09:00:00"
    )
    private LocalTime openingTime;

    @Schema(
            description = "Daily closing time.",
            example = "23:00:00"
    )
    private LocalTime closingTime;
}