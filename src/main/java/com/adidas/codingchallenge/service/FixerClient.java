package com.adidas.codingchallenge.service;

import com.adidas.codingchallenge.model.CurrencyRates;

import feign.Param;
import feign.RequestLine;

public interface FixerClient {

    @RequestLine("GET /latest?access_key={access_key}&base=${base}")
    CurrencyRates getRates(@Param("access_key") String apiKey, @Param("base") String base);

}
