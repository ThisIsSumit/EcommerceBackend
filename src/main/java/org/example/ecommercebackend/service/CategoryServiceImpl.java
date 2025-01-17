package org.example.ecommercebackend.service;

import org.example.ecommercebackend.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl  implements CategoryService{
private List<Category> categories= new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        if(category.getCategoryId()==null){
            category.setCategoryId(categories.size()+1L);
        }
        categories.add(category);

    }
    @Override
    public String deleteCategory(Long categoryId) {

//        categories.removeIf(category -> category.getCategoryId().equals(categoryId));
        Category categoryToDelete= categories.
                stream().
                filter(category -> category.getCategoryId().equals(categoryId)).
                findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"category not found"));
        if(categoryToDelete==null){
            return "category not found";
        }
        categories.remove(categoryToDelete);

        return "category ID : "+categoryId + " has been deleted";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category categoryToUpdate= categories.
                stream().
                filter(category1 -> category1.getCategoryId().equals(categoryId)).
                findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"category not found"));
        if(categoryToUpdate==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"category not found");
        }
        categoryToUpdate.setCategoryName(category.getCategoryName());
        return categoryToUpdate;
    }
}
