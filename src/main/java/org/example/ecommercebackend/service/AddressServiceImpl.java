package org.example.ecommercebackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.ecommercebackend.entities.Address;
import org.example.ecommercebackend.entities.User;
import org.example.ecommercebackend.exceptions.ResourceNotFoundException;
import org.example.ecommercebackend.payload.AddressDTO;
import org.example.ecommercebackend.repositories.AddressRepository;
import org.example.ecommercebackend.repositories.UserRepository;
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

    @Autowired
    UserRepository userRepository;
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

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address =addressRepository.findById(addressId).orElseThrow(()->
                new ResourceNotFoundException("Address",
                        "addressId",
                        "Address not found")
        );
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddress(User user) {
       List<Address> addresses = user.getAddresses();
       return  addresses.stream()
               .map(addressDTO ->
                       modelMapper.map(addressDTO, AddressDTO.class))
               .toList();
    }
    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address addressFromDatabase = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setPincode(addressDTO.getPincode());
        addressFromDatabase.setState(addressDTO.getState());
        addressFromDatabase.setCountry(addressDTO.getCountry());
        addressFromDatabase.setStreet(addressDTO.getStreet());
        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());

        Address updatedAddress = addressRepository.save(addressFromDatabase);

        User user = addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);

        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDatabase = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        User user = addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(addressFromDatabase);

        return "Address deleted successfully with addressId: " + addressId;
    }
}
