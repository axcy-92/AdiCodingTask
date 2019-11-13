package com.adidas.codingchallenge.service;

public interface CurrencyService {
    Double convertTo(String from, String to, Double amount);
    String getDefaultCurrency();
}
