package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Address;
import org.example.ecommercebackend.entities.User;
import org.example.ecommercebackend.payload.AddressDTO;
import org.example.ecommercebackend.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
          Address address =  modelMapper.map(addressDTO, Address.class);
          List<Address> addresses = user.getAddresses();
          addresses.add(address);
          user.setAddresses(addresses);
          address.setUser(user);
        Address savedAddress=  addressRepository.save(address);
          return modelMapper.map(savedAddress, AddressDTO.class);

    }

    @Override
    public List<AddressDTO> getAllAddress() {

        List<Address> address= addressRepository.findAll();
        return address.stream()
                .map(addressDTO ->
                        modelMapper.map(addressDTO, AddressDTO.class))
                .toList();
    }
}
