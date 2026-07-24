package com.fooddelivery.food_delivery_backend.restaurant.dto;

import com.fooddelivery.food_delivery_backend.restaurant.enums.RestaurantStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class RestaurantResponse {

    private Long id;

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

    private boolean open;

    private RestaurantStatus status;

    private Long ownerId;

    private String ownerName;
}