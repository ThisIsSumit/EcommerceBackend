package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.exceptions.APIException;
import org.example.ecommercebackend.exceptions.ResourceNotFoundException;
import org.example.ecommercebackend.payload.CategoryDTO;
import org.example.ecommercebackend.payload.CategoryResponse;
import org.example.ecommercebackend.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl  implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  ModelMapper modelMapper ;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy , String sortOrder) throws APIException
    {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        //pageable is an interface that repersent the request
        //for a specific page of data from database query result
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories= categoryPage.getContent();
        if(categories.isEmpty())
            throw  new APIException("No Category created till now");
        List<CategoryDTO> categoryDTOS= categories
                .stream().
                map(category -> modelMapper.map(category, CategoryDTO.class) )
                .collect(Collectors.toList());
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDb != null) {
            throw new APIException("Category with name "+ category.getCategoryName() +" already exists");
        }
       Category savedCategory= categoryRepository.save(modelMapper.map(category, Category.class));
        return modelMapper.map(savedCategory, CategoryDTO.class);


    }
    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
      Category category = categoryRepository.
              findById(categoryId).
              orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));
      categoryRepository.delete(category);


        return  modelMapper.map(category, CategoryDTO.class);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category category= modelMapper.map(categoryDTO, Category.class);
        Optional<Category> savedCategoryOptional=categoryRepository.findById(categoryId);
        Category savedCategory=savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category","categoryId", categoryId));

        category.setCategoryName(savedCategory.getCategoryName());
        savedCategory=categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
