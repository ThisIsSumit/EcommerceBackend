package org.example.ecommercebackend.repositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.ecommercebackend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

    Category findByCategoryName(@NotBlank(message = "name should not be blank") @Size(min=5,message = "Category name must contain atleast 5 characters") String categoryName);
}
