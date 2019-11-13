package com.adidas.codingchallenge.repository;

import com.adidas.codingchallenge.model.Category;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Override
    Collection<Category> findAll();

}
