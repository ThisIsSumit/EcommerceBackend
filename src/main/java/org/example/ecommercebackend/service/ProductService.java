package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ProductDTO> addProduct(Long categoryId, Product product);

    ProductResponse getAllProduct();
}
