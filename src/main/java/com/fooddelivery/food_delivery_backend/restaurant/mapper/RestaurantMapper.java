package com.fooddelivery.food_delivery_backend.restaurant.mapper;

import com.fooddelivery.food_delivery_backend.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.dto.RestaurantResponse;
import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;

public class RestaurantMapper {

    private RestaurantMapper() {}

    public static Restaurant toEntity(CreateRestaurantRequest request) {

        return Restaurant.builder()
                .name(request.getName())
                .description(request.getDescription())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .openingTime(request.getOpeningTime())
                .closingTime(request.getClosingTime())
                .build();
    }

    public static RestaurantResponse toResponse(Restaurant restaurant) {

        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phone(restaurant.getPhone())
                .email(restaurant.getEmail())
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .state(restaurant.getState())
                .pincode(restaurant.getPincode())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .openingTime(restaurant.getOpeningTime())
                .closingTime(restaurant.getClosingTime())
                .open(restaurant.isOpen())
                .status(restaurant.getStatus())
                .ownerId(restaurant.getOwner().getId())
                .ownerName(restaurant.getOwner().getName())
                .build();
    }
}