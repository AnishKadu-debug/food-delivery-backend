package com.fooddelivery.food_delivery_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.food_delivery_backend.common.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JwtAccessDeniedHandler
        implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        ErrorResponse error = ErrorResponse.builder()

                .success(false)

                .message("Access Denied")

                .errors(List.of("You don't have permission"))

                .timestamp(LocalDateTime.now())

                .build();

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.setContentType("application/json");

        new ObjectMapper().writeValue(
                response.getOutputStream(),
                error);
    }
}