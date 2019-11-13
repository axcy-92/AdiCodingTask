package com.adidas.codingchallenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.adidas.codingchallenge.model.Product;
import com.adidas.codingchallenge.repository.ProductRepository;
import com.adidas.codingchallenge.service.CurrencyService;
import com.adidas.codingchallenge.service.ProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    private Product testProduct;

    @Before
    public void setUp() {
        testProduct = new Product();
        testProduct.setCurrency("GBP");
    }

    @Test
    public void whenGetAll_thenReturnCollectionOfProducts() {
        // given
        when(productRepository.findAll()).thenReturn(Collections.singletonList(testProduct));

        // when
        Collection<Product> results = productRepository.findAll();

        // then
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals(testProduct, results.iterator().next());
    }

    @Test
    public void whenGetById_thenReturnShoe() {
        // given
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

        // when
        Optional<Product> result = productService.getById(1L);

        // then
        assertTrue(result.isPresent());
        assertEquals(testProduct, result.get());
    }

    @Test
    public void whenSaveWithPriceInEur_thenCallCurrencyServiceAndReturnSavedShoe() {
        // given
        when(productRepository.save(testProduct)).thenReturn(testProduct);
        when(currencyService.getDefaultCurrency()).thenReturn("EUR");
        when(currencyService.convertTo(testProduct.getCurrency(), "EUR", null)).thenReturn(111D);

        // when
        Product result = productService.saveWithPriceInEur(testProduct);

        // then
        assertNotNull(result);
        assertEquals("EUR", result.getCurrency());
        assertEquals(111D, result.getPrice(), 0);
    }

}
