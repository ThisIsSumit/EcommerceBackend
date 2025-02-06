package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.payload.CategoryDTO;
import org.example.ecommercebackend.payload.CategoryResponse;

import java.util.List;


public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO ,Long categoryId);


}
