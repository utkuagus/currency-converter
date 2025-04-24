package com.example.currencyConverter.currencies.service;

import com.example.currencyConverter.currencies.entity.Currency;
import com.example.currencyConverter.currencies.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public List<Currency> fetchCurrencyList() {
        return currencyRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Currency updateCurrency(Currency currency, Long currencyId) {
        Currency existingCurrency = getCurrencyById(currencyId);
        if (existingCurrency == null) {
            return null;
        }
        if (!currency.getCode().isEmpty()) {
            existingCurrency.setCode(currency.getCode());
        }
        if (!currency.getName().isEmpty()) {
            existingCurrency.setName(currency.getName());
        }
        if (currency.getUsdRate() != null) {
            existingCurrency.setUsdRate(currency.getUsdRate());
        }
        return currencyRepository.save(existingCurrency);
    }

    @Override
    public Currency updateCurrencyByCode(Currency currency, String code) {
        Currency existingCurrency = getCurrencyByCode(code);
        if (existingCurrency == null) {
            return null;
        }
        return updateCurrency(currency, existingCurrency.getId());
    }

    @Override
    public void deleteCurrencyById(Long currencyId) {
        currencyRepository.deleteById(currencyId);
    }

    @Override
    public Currency getCurrencyById(Long currencyId) {
        return currencyRepository.findById(currencyId).orElse(null);
    }

    @Override
    public Currency getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code).orElse(null);
    }
}
