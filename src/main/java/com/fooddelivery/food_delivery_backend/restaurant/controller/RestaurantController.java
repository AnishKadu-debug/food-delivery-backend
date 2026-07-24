package com.fooddelivery.food_delivery_backend.restaurant.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.dto.RestaurantResponse;
import com.fooddelivery.food_delivery_backend.restaurant.dto.UpdateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<RestaurantResponse> createRestaurant(
            @Valid @RequestBody CreateRestaurantRequest request) {

        return ApiResponse.success(
                "Restaurant created successfully",
                restaurantService.createRestaurant(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<RestaurantResponse> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRestaurantRequest request) {

        return ApiResponse.success(
                "Restaurant updated successfully",
                restaurantService.updateRestaurant(id, request)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<RestaurantResponse> getRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant fetched successfully",
                restaurantService.getRestaurant(id)
        );
    }

    @GetMapping
    public ApiResponse<Page<RestaurantResponse>> getAllRestaurants(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy) {

        return ApiResponse.success(
                "Restaurants fetched successfully",
                restaurantService.getAllRestaurants(page, size, sortBy)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRestaurant(
            @PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ApiResponse.success(
                "Restaurant deleted successfully",
                null
        );
    }

    @GetMapping("/search")
    public ApiResponse<Page<RestaurantResponse>> searchRestaurants(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.success(
                "Restaurants fetched successfully",
                restaurantService.searchRestaurants(
                        keyword,
                        page,
                        size
                )
        );
    }
    @GetMapping("/city")
    public ApiResponse<Page<RestaurantResponse>> getRestaurantsByCity(
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.success(
                "Restaurants fetched successfully",
                restaurantService.getRestaurantsByCity(city, page, size)
        );
    }

    @GetMapping("/pending")
    public ApiResponse<Page<RestaurantResponse>> getPendingRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.success(
                "Pending restaurants fetched successfully",
                restaurantService.getPendingRestaurants(page, size)
        );
    }

    @PatchMapping("/{id}/approve")
    public ApiResponse<RestaurantResponse> approveRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant approved successfully",
                restaurantService.approveRestaurant(id)
        );
    }

    @PatchMapping("/{id}/reject")
    public ApiResponse<RestaurantResponse> rejectRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant rejected successfully",
                restaurantService.rejectRestaurant(id)
        );
    }

    @PatchMapping("/{id}/open")
    public ApiResponse<RestaurantResponse> openRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant opened successfully",
                restaurantService.openRestaurant(id)
        );
    }

    @PatchMapping("/{id}/close")
    public ApiResponse<RestaurantResponse> closeRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant closed successfully",
                restaurantService.closeRestaurant(id)
        );
    }

}