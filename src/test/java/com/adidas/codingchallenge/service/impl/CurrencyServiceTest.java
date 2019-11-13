package com.adidas.codingchallenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.adidas.codingchallenge.model.CurrencyRates;
import com.adidas.codingchallenge.service.FixerClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceTest {

    private static final String TEST_API_KEY = "testApiKey";
    private static final String TEST_BASE_CURRENCY = "EUR";

    @Mock
    private FixerClient fixerClient;

    private CurrencyServiceImpl currencyService;

    @Before
    public void setUp() {
        currencyService = new CurrencyServiceImpl("testUrl");
        currencyService.setBaseCurrency(TEST_BASE_CURRENCY);
        currencyService.setFixerApiKey(TEST_API_KEY);
        currencyService.setFixerClient(fixerClient);
    }

    @Test
    public void whenConvertToDefaultCurrency_thenReturnAmountInEur() {
        // given
        String testCurrency = "GBP";
        Double testAmount = 100D;
        CurrencyRates currencyRates = new CurrencyRates();
        currencyRates.setRates(Collections.singletonMap(testCurrency, 116D));

        // when
        when(fixerClient.getRates(TEST_API_KEY, TEST_BASE_CURRENCY)).thenReturn(currencyRates);
        Double result = currencyService.convertTo(testCurrency, currencyService.getDefaultCurrency(), testAmount);

        // then
        assertEquals(11600D, result, 0);
    }
}
