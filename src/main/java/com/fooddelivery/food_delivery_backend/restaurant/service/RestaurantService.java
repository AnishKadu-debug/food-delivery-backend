package com.fooddelivery.food_delivery_backend.restaurant.service;

import com.fooddelivery.food_delivery_backend.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.dto.RestaurantResponse;
import com.fooddelivery.food_delivery_backend.restaurant.dto.UpdateRestaurantRequest;
import org.springframework.data.domain.Page;

public interface RestaurantService {

    RestaurantResponse createRestaurant(CreateRestaurantRequest request);

    RestaurantResponse updateRestaurant(Long id,
                                        UpdateRestaurantRequest request);

    RestaurantResponse getRestaurant(Long id);

    Page<RestaurantResponse> getAllRestaurants(
            int page,
            int size,
            String sortBy);

    Page<RestaurantResponse> searchRestaurants(
            String keyword,
            int page,
            int size);

    Page<RestaurantResponse> getRestaurantsByCity(
            String city,
            int page,
            int size);

    Page<RestaurantResponse> getPendingRestaurants(
            int page,
            int size);

    RestaurantResponse approveRestaurant(Long id);

    RestaurantResponse rejectRestaurant(Long id);

    RestaurantResponse openRestaurant(Long id);

    RestaurantResponse closeRestaurant(Long id);

    void deleteRestaurant(Long id);
}