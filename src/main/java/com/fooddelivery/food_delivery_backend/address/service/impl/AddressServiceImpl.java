package com.fooddelivery.food_delivery_backend.address.service.impl;

import com.fooddelivery.food_delivery_backend.address.dto.AddressResponse;
import com.fooddelivery.food_delivery_backend.address.dto.CreateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.dto.UpdateAddressRequest;
import com.fooddelivery.food_delivery_backend.address.entity.Address;
import com.fooddelivery.food_delivery_backend.address.mapper.AddressMapper;
import com.fooddelivery.food_delivery_backend.address.repository.AddressRepository;
import com.fooddelivery.food_delivery_backend.address.service.AddressService;
import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CurrentUserService currentUserService;

    @Override
    public AddressResponse createAddress(CreateAddressRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Address address = AddressMapper.toEntity(request);
        address.setUser(currentUser);

        Address savedAddress = addressRepository.save(address);

        return AddressMapper.toResponse(savedAddress);
    }

    @Override
    public AddressResponse updateAddress(Long id, UpdateAddressRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot update this address");
        }

        AddressMapper.updateEntity(address, request);

        Address updatedAddress = addressRepository.save(address);

        return AddressMapper.toResponse(updatedAddress);
    }

    @Override
    public AddressResponse getAddress(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot access this address");
        }

        return AddressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponse> getMyAddresses() {

        User currentUser = currentUserService.getCurrentUser();

        return addressRepository.findByUserId(currentUser.getId())
                .stream()
                .map(AddressMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteAddress(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot delete this address");
        }

        addressRepository.delete(address);
    }

    @Override
    public void setDefaultAddress(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot modify this address");
        }

        addressRepository.findByUserIdAndIsDefaultTrue(currentUser.getId())
                .ifPresent(existingDefault -> {
                    existingDefault.setDefault(false);
                    addressRepository.save(existingDefault);
                });

        address.setDefault(true);
        addressRepository.save(address);
    }
}