package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.exceptions.ResourceNotFoundException;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.example.ecommercebackend.repositories.CategoryRepository;
import org.example.ecommercebackend.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ProductDTO> addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        product.setCategory(category);
        double specialPrice =( product.getDiscount() * 0.01)*product.getPrice();
        product.setPrice(specialPrice);
        productRepository.save(product);
        return ResponseEntity.ok().body(modelMapper.map(product,ProductDTO.class));

    }

    @Override
    public ProductResponse getAllProduct() {
       List<Product> products= productRepository.findAll();
       List<ProductDTO> productdtos= products.stream().map(p->modelMapper.map(p,ProductDTO.class)).toList();
       ProductResponse productResponse= new ProductResponse();
       productResponse.setContent(productdtos);
       return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Product> products= productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productdtos= products.stream().map(p->modelMapper.map(p,ProductDTO.class)).toList();
        ProductResponse productResponse= new ProductResponse();
        productResponse.setContent(productdtos);
        return productResponse;

    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {

        List<Product> products= productRepository.findByProductNameLikeIgnoreCase(keyword);
        List<ProductDTO> productdtos= products.stream().map(p->modelMapper.map(p,ProductDTO.class)).toList();
        ProductResponse productResponse= new ProductResponse();
        productResponse.setContent(productdtos);
        return productResponse;
    }
}
