package com.adidas.codingchallenge.service;

import com.adidas.codingchallenge.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    Collection<Product> getAll();
    Collection<Product> getByCategoryId(Long categoryId);
    Optional<Product> getById(Long productId);
    Product save(Product product);
    void delete(Long id);
}
