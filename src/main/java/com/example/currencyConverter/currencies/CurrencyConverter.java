package com.example.currencyConverter.currencies;

import com.example.currencyConverter.currencies.models.Currency;
import java.util.Optional;

public class CurrencyConverter {

    private CurrencyFetcher currencyFetcher;

    public CurrencyConverter(CurrencyFetcher currencyFetcher) {
        this.currencyFetcher = currencyFetcher;
    }

    private Double getUsdRateByCode(String code) throws Exception {
        Optional<Currency> optionalCurrency = currencyFetcher.getCurrencyList().stream().filter(cur -> cur.getCode().equals(code)).findFirst();
        if(optionalCurrency.isEmpty()) {
            throw new Exception("Invalid currency code");
        }
        return optionalCurrency.get().getUsdRate();
    }

    public Double convertCurrency(Double amount, String fromCode, String toCode) throws Exception {
        Double fromRate = getUsdRateByCode(fromCode);
        Double toRate = getUsdRateByCode(toCode);

        return amount * toRate / fromRate;
    }
}
