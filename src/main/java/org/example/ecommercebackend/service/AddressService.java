package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.User;
import org.example.ecommercebackend.payload.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);
}
