package com.adidas.codingchallenge.service.impl;

import com.adidas.codingchallenge.model.Product;
import com.adidas.codingchallenge.repository.ProductRepository;
import com.adidas.codingchallenge.service.CategoryService;
import com.adidas.codingchallenge.service.CurrencyService;
import com.adidas.codingchallenge.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CurrencyService currencyService;

    @Override
    public Collection<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> getByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Optional<Product> getById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product save(Product product) {
        if (!currencyService.getDefaultCurrency().equalsIgnoreCase(product.getCurrency())) {
            Double priceInDefaultCurrency = currencyService.convertTo(
                    product.getCurrency(),
                    currencyService.getDefaultCurrency(),
                    product.getPrice());
            product.setPrice(priceInDefaultCurrency);
            product.setCurrency(currencyService.getDefaultCurrency());
        }
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
