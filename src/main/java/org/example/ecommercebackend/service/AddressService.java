package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.User;
import org.example.ecommercebackend.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAllAddress();

    AddressDTO getAddressById(Long addressId);
}
