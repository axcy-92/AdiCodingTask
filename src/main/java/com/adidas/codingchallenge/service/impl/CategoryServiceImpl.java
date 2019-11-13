package com.adidas.codingchallenge.service.impl;

import com.adidas.codingchallenge.model.Category;
import com.adidas.codingchallenge.repository.CategoryRepository;
import com.adidas.codingchallenge.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
