package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.payload.CategoryDTO;
import org.example.ecommercebackend.payload.CategoryResponse;

import java.util.List;


public interface CategoryService {

    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category ,Long categoryId);


}
