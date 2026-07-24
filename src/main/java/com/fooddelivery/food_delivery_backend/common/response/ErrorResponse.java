package com.fooddelivery.food_delivery_backend.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;

    private String message;

    private List<String> errors;

    private LocalDateTime timestamp;
}