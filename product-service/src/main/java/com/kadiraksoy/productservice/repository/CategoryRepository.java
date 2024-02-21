package com.kadiraksoy.productservice.repository;

import com.kadiraksoy.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
