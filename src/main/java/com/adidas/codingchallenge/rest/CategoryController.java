package com.adidas.codingchallenge.rest;

import com.adidas.codingchallenge.model.Category;
import com.adidas.codingchallenge.repository.CategoryRepository;

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

import javax.annotation.security.PermitAll;
import java.util.Collection;

@RestController
@RequestMapping({"/category"})
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @PermitAll
    public ResponseEntity<Collection<Category>> getCategories() {
        Collection<Category> categories = categoryRepository.findAll();

        return categories.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("isAnonymous() or isFullyAuthenticated()")
    public ResponseEntity<Category> getById(@PathVariable("id") long id) {
        return categoryRepository.findById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category newCategory = categoryRepository.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
