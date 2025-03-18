package org.example.ecommercebackend.controller;

import jakarta.validation.Valid;
import org.example.ecommercebackend.config.AppConstants;
import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.payload.CategoryDTO;
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


    //@RequestMapping(value = "/api/public/categories", method = RequestMethod.GET)
    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortOrder
    ) {

        return ResponseEntity.ok(categoryService.getAllCategories(pageNumber-1, pageSize,sortBy,sortOrder));
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categorydto) {

       CategoryDTO savedCategoryDTO= categoryService.createCategory(categorydto);
        return  new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
           categoryService.updateCategory(categoryDTO,categoryId);
           return  new ResponseEntity<>("Category has been updated",HttpStatus.OK);
    }



    @DeleteMapping("/api/public/categories/{categoryId}")
    public  ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
           CategoryDTO deleteCategory= categoryService.deleteCategory(categoryId);
            return  ResponseEntity.status(HttpStatus.OK).body(deleteCategory);

    }
}
