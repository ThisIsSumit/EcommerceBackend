package org.example.ecommercebackend.repositories;

import org.example.ecommercebackend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address,Long> {
}
