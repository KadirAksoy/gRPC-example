package com.kadiraksoy.productservice.repository;

import com.kadiraksoy.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
