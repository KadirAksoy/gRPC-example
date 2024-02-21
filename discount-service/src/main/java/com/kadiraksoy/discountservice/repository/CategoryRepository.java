package com.kadiraksoy.discountservice.repository;

import com.kadiraksoy.discountservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByExternalId(String externalId);
}
