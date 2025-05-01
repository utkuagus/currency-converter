package com.example.currencyConverter.db;

import com.example.currencyConverter.currencies.entity.Currency;
import com.example.currencyConverter.currencies.service.CurrencyFetcher;
import com.example.currencyConverter.currencies.service.CurrencyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyDbOperations {
    
    private final CurrencyService currencyService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void fillDb() throws Exception {
        if (!currencyService.fetchCurrencyList().isEmpty()) {
            return;
        }
        List<Currency> currencyList = CurrencyFetcher.getCurrencyList();
        currencyList.forEach(c -> c.setId(null));
        currencyService.saveAll(currencyList);
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    public void updateDb() throws Exception {
        deleteDb();
        fillDb();
    }

    @Transactional
    public void deleteDb() {
        currencyService.deleteAll();
        entityManager.flush();
        entityManager.clear();
        entityManager.createNativeQuery("SELECT setval('currency_id_seq', 1, false)").getSingleResult();
    }
}