package com.fooddelivery.food_delivery_backend.restaurant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateRestaurantRequest {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String phone;

    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String pincode;

    private Double latitude;

    private Double longitude;

    private LocalTime openingTime;

    private LocalTime closingTime;
}