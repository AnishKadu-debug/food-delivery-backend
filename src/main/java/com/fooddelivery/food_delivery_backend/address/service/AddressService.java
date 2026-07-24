package com.fooddelivery.food_delivery_backend.address.service;

import com.fooddelivery.food_delivery_backend.address.dto.AddressResponse;
import com.fooddelivery.food_delivery_backend.address.dto.CreateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.dto.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressResponse createAddress(CreateAddressRequest request);

    AddressResponse updateAddress(Long id,
                                  UpdateAddressRequest request);

    AddressResponse getAddress(Long id);

    List<AddressResponse> getMyAddresses();

    void deleteAddress(Long id);

    void setDefaultAddress(Long id);
}