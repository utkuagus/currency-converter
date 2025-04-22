package com.example.currencyConverter.currencies.controller;

import com.example.currencyConverter.currencies.entity.Currency;
import com.example.currencyConverter.currencies.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired private CurrencyService currencyService;

    @PostMapping
    public Currency saveCurrency(
            @Valid @RequestBody Currency currency)
    {
        return currencyService.saveCurrency(currency);
    }

    @GetMapping
    public List<Currency> fetchCurrencyList()
    {
        return currencyService.fetchCurrencyList();
    }

    @PutMapping("/{id}")
    public Currency updateCurrency(@RequestBody Currency currency, @PathVariable("id") Long currencyId)
    {
        return currencyService.updateCurrency(currency, currencyId);
    }

    @DeleteMapping("/{id}")
    public String deleteCurrencyById(@PathVariable("id")
                                       Long currencyId)
    {
        currencyService.deleteCurrencyById(currencyId);
        return "Deleted Successfully";
    }
    
}
