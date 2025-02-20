package org.example.ecommercebackend.repositories;

import org.example.ecommercebackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long> {


}
