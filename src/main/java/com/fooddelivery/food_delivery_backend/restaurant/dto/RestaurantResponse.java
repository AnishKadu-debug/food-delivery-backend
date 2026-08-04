package com.fooddelivery.food_delivery_backend.restaurant.dto;

import com.fooddelivery.food_delivery_backend.restaurant.enums.RestaurantStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
@Schema(description = "Represents restaurant information returned by the API.")
public class RestaurantResponse {

    @Schema(
            description = "Unique identifier of the restaurant.",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the restaurant.",
            example = "Spice Garden"
    )
    private String name;

    @Schema(
            description = "Brief description of the restaurant.",
            example = "Authentic North Indian and Chinese cuisine."
    )
    private String description;

    @Schema(
            description = "Restaurant contact number.",
            example = "9876543210"
    )
    private String phone;

    @Schema(
            description = "Restaurant contact email.",
            example = "contact@spicegarden.com"
    )
    private String email;

    @Schema(
            description = "Street address of the restaurant.",
            example = "123 MG Road"
    )
    private String address;

    @Schema(
            description = "City where the restaurant is located.",
            example = "Pune"
    )
    private String city;

    @Schema(
            description = "State where the restaurant is located.",
            example = "Maharashtra"
    )
    private String state;

    @Schema(
            description = "Postal PIN code.",
            example = "411001"
    )
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

    @Schema(
            description = "Whether the restaurant is currently accepting orders.",
            example = "true"
    )
    private boolean open;

    @Schema(
            description = "Current approval and operational status of the restaurant.",
            example = "APPROVED"
    )
    private RestaurantStatus status;

    @Schema(
            description = "Unique identifier of the restaurant owner.",
            example = "5"
    )
    private Long ownerId;

    @Schema(
            description = "Full name of the restaurant owner.",
            example = "Rahul Sharma"
    )
    private String ownerName;
}