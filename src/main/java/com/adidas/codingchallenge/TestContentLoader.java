package com.adidas.codingchallenge;

import com.adidas.codingchallenge.model.Category;
import com.adidas.codingchallenge.model.Product;
import com.adidas.codingchallenge.repository.CategoryRepository;
import com.adidas.codingchallenge.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * This is not a beautiful approach, but the fastest and acceptable for a demo is not it?
 */
@Component
public class TestContentLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Category root = new Category();
        root.setName("Root Category");
        root = categoryRepository.save(root);

        Category subCategory = new Category();
        subCategory.setName("Sub Category");
        subCategory.setParent(root);
        subCategory = categoryRepository.save(subCategory);

        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory(subCategory);
        productRepository.save(product);
    }
}
