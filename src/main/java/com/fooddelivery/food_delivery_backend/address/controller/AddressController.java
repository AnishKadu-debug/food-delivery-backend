package com.fooddelivery.food_delivery_backend.address.controller;

import com.fooddelivery.food_delivery_backend.address.dto.AddressResponse;
import com.fooddelivery.food_delivery_backend.address.dto.CreateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.dto.UpdateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.service.AddressService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ApiResponse<AddressResponse> createAddress(
            @Valid @RequestBody CreateAddressRequest request) {

        return ApiResponse.success(
                "Address created successfully",
                addressService.createAddress(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<AddressResponse> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAddressRequest request) {

        return ApiResponse.success(
                "Address updated successfully",
                addressService.updateAddress(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<AddressResponse> getAddress(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Address fetched successfully",
                addressService.getAddress(id));
    }

    @GetMapping
    public ApiResponse<List<AddressResponse>> getMyAddresses() {

        return ApiResponse.success(
                "Addresses fetched successfully",
                addressService.getMyAddresses());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAddress(
            @PathVariable Long id) {

        addressService.deleteAddress(id);

        return ApiResponse.success(
                "Address deleted successfully",
                null);
    }

    @PutMapping("/{id}/default")
    public ApiResponse<Void> setDefaultAddress(
            @PathVariable Long id) {

        addressService.setDefaultAddress(id);

        return ApiResponse.success(
                "Default address updated successfully",
                null);
    }
}