package com.fooddelivery.food_delivery_backend.restaurant.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UpdateRestaurantRequest {

    private String name;

    private String description;

    private String phone;

    private String email;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private Double latitude;

    private Double longitude;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private Boolean open;
}