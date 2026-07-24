package com.fooddelivery.food_delivery_backend.restaurant.service;

import com.fooddelivery.food_delivery_backend.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse createRestaurant(CreateRestaurantRequest request);

    RestaurantResponse getRestaurant(Long id);

    List<RestaurantResponse> getAllRestaurants();

    void deleteRestaurant(Long id);
}