package com.clothing.clothingapi.repository;

import com.clothing.clothingapi.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // SEARCH by keyword (title)
    List<Product> findByTitleContainingIgnoreCase(String keyword);

    // FILTER by category
    List<Product> findByCategory(String category);
}