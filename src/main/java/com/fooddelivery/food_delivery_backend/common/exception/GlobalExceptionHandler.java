package com.fooddelivery.food_delivery_backend.common.exception;

import com.fooddelivery.food_delivery_backend.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .errors(List.of(ex.getMessage()))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .errors(List.of(ex.getMessage()))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {

        return ResponseEntity.badRequest()
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .errors(List.of(ex.getMessage()))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .errors(List.of(ex.getMessage()))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message(ex.getMessage())
                                .errors(List.of(ex.getMessage()))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest()
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message("Validation failed")
                                .errors(errors)
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        return ResponseEntity.internalServerError()
                .body(
                        ErrorResponse.builder()
                                .success(false)
                                .message("Internal Server Error")
                                .errors(List.of(ex.getMessage()))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}