package com.fooddelivery.food_delivery_backend.address.controller;

import com.fooddelivery.food_delivery_backend.address.dto.AddressResponse;
import com.fooddelivery.food_delivery_backend.address.dto.CreateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.dto.UpdateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.service.AddressService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
@Tag(
        name = "Addresses",
        description = "APIs for managing customer delivery addresses."
)
public class AddressController {

    private final AddressService addressService;

    @Operation(
            summary = "Create a new address",
            description = "Creates a new delivery address for the authenticated customer."
    )
    @PostMapping
    public ApiResponse<AddressResponse> createAddress(
            @Valid @RequestBody CreateAddressRequest request) {

        return ApiResponse.success(
                "Address created successfully",
                addressService.createAddress(request)
        );
    }

    @Operation(
            summary = "Update an address",
            description = "Updates an existing delivery address belonging to the authenticated customer."
    )
    @PutMapping("/{id}")
    public ApiResponse<AddressResponse> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAddressRequest request) {

        return ApiResponse.success(
                "Address updated successfully",
                addressService.updateAddress(id, request)
        );
    }

    @Operation(
            summary = "Get address by ID",
            description = "Returns a delivery address by its unique identifier."
    )
    @GetMapping("/{id}")
    public ApiResponse<AddressResponse> getAddress(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Address fetched successfully",
                addressService.getAddress(id)
        );
    }

    @Operation(
            summary = "Get my addresses",
            description = "Returns all delivery addresses of the authenticated customer."
    )
    @GetMapping
    public ApiResponse<List<AddressResponse>> getMyAddresses() {

        return ApiResponse.success(
                "Addresses fetched successfully",
                addressService.getMyAddresses()
        );
    }

    @Operation(
            summary = "Delete an address",
            description = "Deletes a delivery address belonging to the authenticated customer."
    )
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAddress(
            @PathVariable Long id) {

        addressService.deleteAddress(id);

        return ApiResponse.success(
                "Address deleted successfully",
                null
        );
    }

    @Operation(
            summary = "Set default address",
            description = "Marks the specified delivery address as the default address for the authenticated customer."
    )
    @PutMapping("/{id}/default")
    public ApiResponse<Void> setDefaultAddress(
            @PathVariable Long id) {

        addressService.setDefaultAddress(id);

        return ApiResponse.success(
                "Default address updated successfully",
                null
        );
    }
}