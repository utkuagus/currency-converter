package com.example.currencyConverter.currencies.service;

import com.example.currencyConverter.currencies.entity.Currency;

import java.util.List;

public interface CurrencyService {
    // Save operation
    Currency saveCurrency(Currency currency);

    // Read operation
    List<Currency> fetchCurrencyList();

    // Update operation
    Currency updateCurrency(Currency currency,
                                Long currencyId);

    Currency updateCurrencyByCode(Currency currency, String code);

    // Delete operation
    void deleteCurrencyById(Long currencyId);

    Currency getCurrencyById(Long currencyId);

    Currency getCurrencyByCode(String code);
}
