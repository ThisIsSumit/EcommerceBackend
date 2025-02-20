package org.example.ecommercebackend.repositories;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {

    List<Product> findByCategoryOrderByPriceAsc(Category category);
}
