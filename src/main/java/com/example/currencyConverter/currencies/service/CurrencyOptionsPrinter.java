package com.example.currencyConverter.currencies.service;

import com.example.currencyConverter.currencies.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CurrencyOptionsPrinter {

    @Autowired
    private CurrencyService currencyService;

    public String getCurrencyOptions(String selectedCode) throws Exception {
        List<Currency> currencyList = currencyService.fetchCurrencyList();
        StringBuilder text = new StringBuilder("<option value=\"\"");
        text.append(selectedCode == null || selectedCode.isEmpty() ? " selected" : "")
                .append(">--Please choose an option--</option>");
        for (Currency currency : currencyList) {
            text.append("<option value=\"").append(currency.getCode()).append("\"")
                    .append(Objects.equals(currency.getCode(), selectedCode) ? " selected>" : ">")
                    .append(currency.getName()).append("</option>");
        }
        return text.toString();
    }
}
