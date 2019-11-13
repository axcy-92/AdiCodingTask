package com.adidas.codingchallenge.rest;

import com.adidas.codingchallenge.model.Product;
import com.adidas.codingchallenge.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping({"/product"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("isAnonymous() or isFullyAuthenticated()")
    public ResponseEntity<Collection<Product>> getProducts() {
        Collection<Product> products = productService.getAll();

        return products.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = {"byCategory/{id}"})
    @PreAuthorize("isAnonymous() or isFullyAuthenticated()")
    public ResponseEntity<Collection<Product>> getProductsByCategory(@PathVariable("id") long id) {
        Collection<Product> products = productService.getByCategoryId(id);

        return products.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("isAnonymous() or isFullyAuthenticated()")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        return productService.getById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
