package com.adidas.codingchallenge.repository;

import com.adidas.codingchallenge.model.Category;
import com.adidas.codingchallenge.model.Product;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    Collection<Product> findAll();

    Collection<Product> findByCategoryId(Long id);

}
