package com.kadiraksoy.productservice.service;

import com.kadiraksoy.productservice.model.Category;
import com.kadiraksoy.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category add(Category category){
        return categoryRepository.save(category);
    }
}
