package org.example.ecommercebackend.controller;

import jakarta.validation.Valid;
import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.example.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
           @Valid @RequestBody ProductDTO productdto,
            @PathVariable Long categoryId) {

       ProductDTO savedProduct= productService.addProduct(categoryId,productdto);

       return new ResponseEntity<>(savedProduct, HttpStatus.OK);

    }

    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
       ProductResponse productResponse= productService.getAllProduct();
       return ResponseEntity.ok().body(productResponse.getContent());
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable Long categoryId) {
        ProductResponse productResponse= productService.searchByCategory(categoryId);
        return ResponseEntity.ok().body(productResponse);
    }



    @GetMapping("/public/products/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword) {
    ProductResponse productResponse=  productService.searchProductByKeyword(keyword);
      return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }
    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct( @Valid @RequestBody ProductDTO productdto, @PathVariable Long productId) {
        ProductDTO updatedProduct = productService.updateProduct(productId,productdto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);


    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
        ProductDTO productDTO= productService.deleteProduct(productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam("image") MultipartFile image ) throws IOException {

   ProductDTO updatedProduct= productService.updateProductImage(productId,image);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
