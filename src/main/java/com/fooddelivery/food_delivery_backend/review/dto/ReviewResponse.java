package com.fooddelivery.food_delivery_backend.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a restaurant review returned by the API.")
public class ReviewResponse {

    @Schema(
            description = "Unique identifier of the review.",
            example = "10"
    )
    private Long id;

    @Schema(
            description = "Identifier of the customer who wrote the review.",
            example = "7"
    )
    private Long customerId;

    @Schema(
            description = "Name of the customer.",
            example = "Rahul Sharma"
    )
    private String customerName;

    @Schema(
            description = "Identifier of the reviewed restaurant.",
            example = "5"
    )
    private Long restaurantId;

    @Schema(
            description = "Name of the reviewed restaurant.",
            example = "Spice Garden"
    )
    private String restaurantName;

    @Schema(
            description = "Rating given by the customer.",
            example = "5"
    )
    private Integer rating;

    @Schema(
            description = "Customer's review comment.",
            example = "Excellent food, quick delivery, and great service."
    )
    private String comment;
}