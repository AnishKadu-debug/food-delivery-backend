package com.fooddelivery.food_delivery_backend.user.service.impl;

import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void save_shouldReturnSavedUser() {

        when(userRepository.save(user))
                .thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        assertSame(user, result);

        verify(userRepository).save(user);
    }

    @Test
    void findByEmail_shouldReturnUserWhenEmailExists() {

        String email = "anish@example.com";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        Optional<User> result =
                userService.findByEmail(email);

        assertTrue(result.isPresent());
        assertSame(user, result.get());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void findByEmail_shouldReturnEmptyWhenEmailDoesNotExist() {

        String email = "unknown@example.com";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        Optional<User> result =
                userService.findByEmail(email);

        assertTrue(result.isEmpty());

        verify(userRepository).findByEmail(email);
    }

    @Test
    void existsByEmail_shouldReturnTrueWhenEmailExists() {

        String email = "anish@example.com";

        when(userRepository.existsByEmail(email))
                .thenReturn(true);

        boolean result =
                userService.existsByEmail(email);

        assertTrue(result);

        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByEmail_shouldReturnFalseWhenEmailDoesNotExist() {

        String email = "unknown@example.com";

        when(userRepository.existsByEmail(email))
                .thenReturn(false);

        boolean result =
                userService.existsByEmail(email);

        assertFalse(result);

        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByPhone_shouldReturnTrueWhenPhoneExists() {

        String phone = "9876543210";

        when(userRepository.existsByPhone(phone))
                .thenReturn(true);

        boolean result =
                userService.existsByPhone(phone);

        assertTrue(result);

        verify(userRepository).existsByPhone(phone);
    }

    @Test
    void existsByPhone_shouldReturnFalseWhenPhoneDoesNotExist() {

        String phone = "9999999999";

        when(userRepository.existsByPhone(phone))
                .thenReturn(false);

        boolean result =
                userService.existsByPhone(phone);

        assertFalse(result);

        verify(userRepository).existsByPhone(phone);
    }
    @Test
    void save_shouldThrowExceptionWhenRepositoryFails() {

        when(userRepository.save(user))
                .thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.save(user)
        );

        assertEquals("Database error", exception.getMessage());

        verify(userRepository).save(user);
    }
}