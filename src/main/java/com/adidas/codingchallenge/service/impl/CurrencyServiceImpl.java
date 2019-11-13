package com.adidas.codingchallenge.service.impl;

import com.adidas.codingchallenge.model.CurrencyRates;
import com.adidas.codingchallenge.service.CurrencyService;
import com.adidas.codingchallenge.service.FixerClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import feign.Feign;
import feign.Logger;
import feign.Logger.Level;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.AccessLevel;
import lombok.Setter;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${fixer.api.key}")
    @Setter(AccessLevel.PACKAGE)
    private String fixerApiKey;

    @Value("${fixer.baseCurrency}")
    @Setter(AccessLevel.PACKAGE)
    private String baseCurrency;

    @Setter(AccessLevel.PACKAGE)
    private FixerClient fixerClient;

    public CurrencyServiceImpl(@Value("${fixer.api.url}") String fixerApiUrl) {
        fixerClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Level.BASIC)
                .target(FixerClient.class, fixerApiUrl);
    }

    @Override
    public Double convertTo(String from, String to, Double amount) {
        CurrencyRates result = fixerClient.getRates(fixerApiKey, to);
        Double rate = result.getRates().get(from);
        return amount * rate;
    }

    @Override
    public String getDefaultCurrency() {
        return baseCurrency;
    }

}
