package com.fooddelivery.food_delivery_backend.review.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.review.dto.CreateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.dto.ReviewResponse;
import com.fooddelivery.food_delivery_backend.review.dto.UpdateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponse> createReview(
            @Valid @RequestBody CreateReviewRequest request) {

        return ApiResponse.success(
                "Review created successfully",
                reviewService.createReview(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<ReviewResponse> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReviewRequest request) {

        return ApiResponse.success(
                "Review updated successfully",
                reviewService.updateReview(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteReview(
            @PathVariable Long id) {

        reviewService.deleteReview(id);

        return ApiResponse.success(
                "Review deleted successfully",
                null
        );
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ApiResponse<List<ReviewResponse>> getRestaurantReviews(
            @PathVariable Long restaurantId) {

        return ApiResponse.success(
                "Reviews fetched successfully",
                reviewService.getRestaurantReviews(restaurantId)
        );
    }
}