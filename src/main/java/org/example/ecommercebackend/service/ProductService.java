package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, ProductDTO productdto);

    ProductResponse getAllProduct();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO productdto);

    ProductDTO deleteProduct(Long productId);
}
