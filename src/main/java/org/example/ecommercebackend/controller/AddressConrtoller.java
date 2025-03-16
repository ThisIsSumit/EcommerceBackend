package org.example.ecommercebackend.controller;

import org.example.ecommercebackend.entities.User;
import org.example.ecommercebackend.payload.AddressDTO;
import org.example.ecommercebackend.service.AddressService;
import org.example.ecommercebackend.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressConrtoller {
    @Autowired
    private AddressService addressService;
    @Autowired
    AuthUtil authUtil;

    @PostMapping("/adresses")

    public ResponseEntity<AddressDTO> addAddress(@RequestParam AddressDTO addressDTO) {
        User user =authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO,user);
return new ResponseEntity<>(savedAddressDTO,HttpStatus.CREATED);
 }

  @GetMapping("/address")
    public ResponseEntity<List<AddressDTO>> getAddresses() {
        User user =authUtil.loggedInUser();
        List<AddressDTO> allAddresses= addressService.getAllAddress();
        return new ResponseEntity<>(allAddresses,HttpStatus.OK);

  }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(
            @PathVariable Long addressId) {
        User user =authUtil.loggedInUser();
        AddressDTO addressDTO= addressService.getAddressById(addressId);
        return new ResponseEntity<>(addressDTO,HttpStatus.OK);

    }


    @GetMapping("/users/address")
    public ResponseEntity<List<AddressDTO>> getUserAddress(
             ) {
        User user =authUtil.loggedInUser();
        List<AddressDTO> addressDTO= addressService.getUserAddress(user);
        return new ResponseEntity<>(addressDTO,HttpStatus.OK);

    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId
            , @RequestBody AddressDTO addressDTO){
        AddressDTO updatedAddress = addressService.updateAddress(addressId, addressDTO);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
