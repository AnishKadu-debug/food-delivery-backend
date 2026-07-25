package com.fooddelivery.food_delivery_backend.user.dto;

import com.fooddelivery.food_delivery_backend.user.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Role role;

}