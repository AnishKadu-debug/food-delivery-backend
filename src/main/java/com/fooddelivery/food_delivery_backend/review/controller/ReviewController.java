package com.fooddelivery.food_delivery_backend.review.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.review.dto.CreateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.dto.ReviewResponse;
import com.fooddelivery.food_delivery_backend.review.dto.UpdateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(
        name = "Reviews",
        description = "APIs for creating, updating, deleting, and viewing restaurant reviews."
)
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "Create review",
            description = "Creates a review for a restaurant by the authenticated customer."
    )
    @PostMapping
    public ApiResponse<ReviewResponse> createReview(
            @Valid @RequestBody CreateReviewRequest request) {

        return ApiResponse.success(
                "Review created successfully",
                reviewService.createReview(request)
        );
    }

    @Operation(
            summary = "Update review",
            description = "Updates an existing review created by the authenticated customer."
    )
    @PutMapping("/{id}")
    public ApiResponse<ReviewResponse> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReviewRequest request) {

        return ApiResponse.success(
                "Review updated successfully",
                reviewService.updateReview(id, request)
        );
    }

    @Operation(
            summary = "Delete review",
            description = "Deletes a review created by the authenticated customer."
    )
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteReview(
            @PathVariable Long id) {

        reviewService.deleteReview(id);

        return ApiResponse.success(
                "Review deleted successfully",
                null
        );
    }

    @Operation(
            summary = "Get restaurant reviews",
            description = "Returns all reviews for a specific restaurant."
    )
    @GetMapping("/restaurant/{restaurantId}")
    public ApiResponse<List<ReviewResponse>> getRestaurantReviews(
            @PathVariable Long restaurantId) {

        return ApiResponse.success(
                "Reviews fetched successfully",
                reviewService.getRestaurantReviews(restaurantId)
        );
    }
}