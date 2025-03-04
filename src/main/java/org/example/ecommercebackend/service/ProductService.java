package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ProductDTO> addProduct(Long categoryId, Product product);

    ProductResponse getAllProduct();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, Product product);
}
