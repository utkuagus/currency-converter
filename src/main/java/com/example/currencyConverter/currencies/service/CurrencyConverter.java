package com.example.currencyConverter.currencies.service;

import com.example.currencyConverter.currencies.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter {

    @Autowired
    private CurrencyService currencyService;

    private Double getUsdRateByCode(String code) {
        Currency currency = currencyService.getCurrencyByCode(code);
        return currency.getUsdRate();
    }

    public Double convertCurrency(Double amount, String fromCode, String toCode) {
        Double fromRate = getUsdRateByCode(fromCode);
        Double toRate = getUsdRateByCode(toCode);

        return amount * toRate / fromRate;
    }
}
