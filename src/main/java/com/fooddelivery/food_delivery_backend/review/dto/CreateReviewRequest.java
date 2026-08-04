package com.fooddelivery.food_delivery_backend.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating a restaurant review.")
public class CreateReviewRequest {

    @Schema(
            description = "Identifier of the restaurant being reviewed.",
            example = "5"
    )
    @NotNull(message = "Restaurant is required")
    private Long restaurantId;

    @Schema(
            description = "Rating given to the restaurant on a scale of 1 to 5.",
            example = "5"
    )
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Minimum rating is 1")
    @Max(value = 5, message = "Maximum rating is 5")
    private Integer rating;

    @Schema(
            description = "Customer's review comment.",
            example = "Excellent food, quick delivery, and great service."
    )
    @NotBlank(message = "Comment is required")
    private String comment;
}