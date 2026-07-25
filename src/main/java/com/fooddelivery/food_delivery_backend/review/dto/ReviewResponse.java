package com.fooddelivery.food_delivery_backend.review.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    private Long id;

    private Long customerId;

    private String customerName;

    private Long restaurantId;

    private String restaurantName;

    private Integer rating;

    private String comment;
}