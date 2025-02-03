package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.exceptions.APIException;
import org.example.ecommercebackend.exceptions.ResourceNotFoundException;
import org.example.ecommercebackend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements CategoryService{
//private List<Category> categories= new ArrayList<>();


    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categories= categoryRepository.findAll();
        if(categories.isEmpty())
            throw  new APIException("No Category created till now");

        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIException("Category with name "+ category.getCategoryName() +" already exists");
        }
        categoryRepository.save(category);


    }
    @Override
    public String deleteCategory(Long categoryId) {

      Category category = categoryRepository.
              findById(categoryId).
              orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));
      categoryRepository.delete(category);


        return "category ID : "+categoryId + " has been deleted";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional=categoryRepository.findById(categoryId);
        Category savedCategory=savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));

        category.setCategoryName(savedCategory.getCategoryName());

        savedCategory=categoryRepository.save(category);
        return savedCategory;
    }
}
