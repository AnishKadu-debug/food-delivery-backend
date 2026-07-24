package com.fooddelivery.food_delivery_backend.address.mapper;

import com.fooddelivery.food_delivery_backend.address.dto.AddressResponse;
import com.fooddelivery.food_delivery_backend.address.dto.CreateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.dto.UpdateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.entity.Address;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static Address toEntity(CreateAddressRequest request) {

        return Address.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .landmark(request.getLandmark())
                .build();
    }

    public static void updateEntity(Address address,
                                    UpdateAddressRequest request) {

        address.setFullName(request.getFullName());
        address.setPhone(request.getPhone());
        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setLandmark(request.getLandmark());
    }

    public static AddressResponse toResponse(Address address) {

        return AddressResponse.builder()
                .id(address.getId())
                .fullName(address.getFullName())
                .phone(address.getPhone())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .landmark(address.getLandmark())
                .isDefault(address.isDefault())
                .build();
    }
}