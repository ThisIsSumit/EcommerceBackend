package org.example.ecommercebackend.controller;

import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.example.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
            @RequestBody Product product,
            @PathVariable Long categoryId) {

       return  productService.addProduct(categoryId,product);

    }

    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
       ProductResponse productResponse= productService.getAllProduct();
       return ResponseEntity.ok().body(productResponse.getContent());
    }

}
