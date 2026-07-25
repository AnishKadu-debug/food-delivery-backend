package com.fooddelivery.food_delivery_backend.review.service;

import com.fooddelivery.food_delivery_backend.review.dto.CreateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.dto.ReviewResponse;
import com.fooddelivery.food_delivery_backend.review.dto.UpdateReviewRequest;

import java.util.List;

public interface ReviewService {

    ReviewResponse createReview(CreateReviewRequest request);

    ReviewResponse updateReview(Long reviewId,
                                UpdateReviewRequest request);

    void deleteReview(Long reviewId);

    List<ReviewResponse> getRestaurantReviews(Long restaurantId);

}