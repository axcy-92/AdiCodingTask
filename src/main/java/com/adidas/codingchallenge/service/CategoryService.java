package com.adidas.codingchallenge.service;

import com.adidas.codingchallenge.model.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryService {
    Collection<Category> getAll();
    Optional<Category> getById(Long categoryId);
    Category save(Category category);
    void delete(Long id);
}
