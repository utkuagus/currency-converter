package com.example.currencyConverter.db;

import com.example.currencyConverter.currencies.repository.CurrencyRepository;
import com.example.currencyConverter.currencies.service.CurrencyFetcher;
import com.example.currencyConverter.currencies.entity.Currency;
import com.example.currencyConverter.currencies.service.CurrencyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor // Lombok: generates constructor for final fields
public class CurrencyDbOperations {

    private final DataSource dataSource;
    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private void callSql(String sql, Connection connection) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

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

//    @Transactional
//    public void batchUpdateUsers(List<Currency> currencyList) {
//        Set<Long> existingIds = new HashSet<>(
//                entityManager.createQuery("SELECT c.id FROM Currency c WHERE c.id IN :ids", Long.class)
//                        .setParameter("ids", currencyList.stream().map(Currency::getId).collect(Collectors.toList()))
//                        .getResultList()
//        );
//        int batchSize = 50; // You can tune this size based on your system
//        for (int i = 0; i < currencyList.size(); i++) {
//            Currency newCurrency = currencyList.get(i);
//            if (existingIds.contains(newCurrency.getId())) {
//                entityManager.merge(newCurrency);
//            } else {
//                entityManager.persist(newCurrency);
//            }
//
//            if (i > 0 && i % batchSize == 0) {
//                entityManager.flush();
//                entityManager.clear();
//            }
//        }
//
//        // Flush remaining items
//        entityManager.flush();
//        entityManager.clear();
//    }
}