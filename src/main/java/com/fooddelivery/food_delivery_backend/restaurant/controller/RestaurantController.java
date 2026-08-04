package com.fooddelivery.food_delivery_backend.restaurant.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.dto.RestaurantResponse;
import com.fooddelivery.food_delivery_backend.restaurant.dto.UpdateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Tag(
        name = "Restaurants",
        description = "APIs for creating, managing, searching, and moderating restaurants."
)
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(
            summary = "Create a restaurant",
            description = "Creates a new restaurant for the authenticated restaurant owner."
    )
    @PostMapping
    public ApiResponse<RestaurantResponse> createRestaurant(
            @Valid @RequestBody CreateRestaurantRequest request) {

        return ApiResponse.success(
                "Restaurant created successfully",
                restaurantService.createRestaurant(request)
        );
    }

    @Operation(
            summary = "Update restaurant",
            description = "Updates an existing restaurant owned by the authenticated owner."
    )
    @PutMapping("/{id}")
    public ApiResponse<RestaurantResponse> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRestaurantRequest request) {

        return ApiResponse.success(
                "Restaurant updated successfully",
                restaurantService.updateRestaurant(id, request)
        );
    }

    @Operation(
            summary = "Get restaurant by ID",
            description = "Returns complete information about a restaurant."
    )
    @GetMapping("/{id}")
    public ApiResponse<RestaurantResponse> getRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant fetched successfully",
                restaurantService.getRestaurant(id)
        );
    }

    @Operation(
            summary = "Get all restaurants",
            description = "Returns a paginated list of all restaurants."
    )
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

    @Operation(
            summary = "Delete restaurant",
            description = "Deletes a restaurant owned by the authenticated owner."
    )
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRestaurant(
            @PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ApiResponse.success(
                "Restaurant deleted successfully",
                null
        );
    }

    @Operation(
            summary = "Search restaurants",
            description = "Searches restaurants by a keyword."
    )
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

    @Operation(
            summary = "Get restaurants by city",
            description = "Returns restaurants located in the specified city."
    )
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

    @Operation(
            summary = "Get pending restaurants",
            description = "Returns all restaurants waiting for admin approval."
    )
    @GetMapping("/pending")
    public ApiResponse<Page<RestaurantResponse>> getPendingRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ApiResponse.success(
                "Pending restaurants fetched successfully",
                restaurantService.getPendingRestaurants(page, size)
        );
    }

    @Operation(
            summary = "Approve restaurant",
            description = "Approves a pending restaurant. Accessible only to administrators."
    )
    @PatchMapping("/{id}/approve")
    public ApiResponse<RestaurantResponse> approveRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant approved successfully",
                restaurantService.approveRestaurant(id)
        );
    }

    @Operation(
            summary = "Reject restaurant",
            description = "Rejects a pending restaurant. Accessible only to administrators."
    )
    @PatchMapping("/{id}/reject")
    public ApiResponse<RestaurantResponse> rejectRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant rejected successfully",
                restaurantService.rejectRestaurant(id)
        );
    }

    @Operation(
            summary = "Open restaurant",
            description = "Marks the restaurant as open for accepting new orders."
    )
    @PatchMapping("/{id}/open")
    public ApiResponse<RestaurantResponse> openRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant opened successfully",
                restaurantService.openRestaurant(id)
        );
    }

    @Operation(
            summary = "Close restaurant",
            description = "Marks the restaurant as closed and unavailable for accepting new orders."
    )
    @PatchMapping("/{id}/close")
    public ApiResponse<RestaurantResponse> closeRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Restaurant closed successfully",
                restaurantService.closeRestaurant(id)
        );
    }
}