package com.fooddelivery.food_delivery_backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.food_delivery_backend.common.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {

        ErrorResponse error = ErrorResponse.builder()
                .success(false)
                .message("Unauthorized")
                .errors(List.of("Authentication is required"))
                .timestamp(LocalDateTime.now())
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType("application/json");

        new ObjectMapper().writeValue(
                response.getOutputStream(),
                error);
    }
}