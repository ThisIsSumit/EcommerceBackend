package org.example.ecommercebackend.service;

import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, ProductDTO productdto);

    ProductResponse getAllProduct(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse searchByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, Long categoryId);

    ProductResponse searchProductByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO productdto);

    ProductDTO deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
