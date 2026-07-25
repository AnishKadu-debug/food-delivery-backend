package com.fooddelivery.food_delivery_backend.review.mapper;

import com.fooddelivery.food_delivery_backend.review.dto.ReviewResponse;
import com.fooddelivery.food_delivery_backend.review.entity.Review;

public class ReviewMapper {

    private ReviewMapper() {
    }

    public static ReviewResponse toResponse(Review review) {

        return ReviewResponse.builder()
                .id(review.getId())
                .customerId(review.getCustomer().getId())
                .customerName(review.getCustomer().getName())
                .restaurantId(review.getRestaurant().getId())
                .restaurantName(review.getRestaurant().getName())
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}