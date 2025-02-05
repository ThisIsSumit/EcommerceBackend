package org.example.ecommercebackend.controller;

import jakarta.validation.Valid;
import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.payload.CategoryResponse;
import org.example.ecommercebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
//@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;


   // @GetMapping("/api/public/categories")
    @RequestMapping(value = "/api/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
       categoryService.createCategory(category);
        return  new ResponseEntity<>("Category has been created",HttpStatus.CREATED);
    }
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId) {
           categoryService.updateCategory(category,categoryId);
           return  new ResponseEntity<>("Category has been updated",HttpStatus.OK);
    }



    @DeleteMapping("/api/public/categories/{categoryId}")
    public  ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
           String status= categoryService.deleteCategory(categoryId);
            return  ResponseEntity.status(HttpStatus.OK).body(status);

    }
}
