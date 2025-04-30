package com.example.currencyConverter.db;

import com.example.currencyConverter.currencies.repository.CurrencyRepository;
import com.example.currencyConverter.currencies.service.CurrencyFetcher;
import com.example.currencyConverter.currencies.entity.Currency;
import com.example.currencyConverter.currencies.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor // Lombok: generates constructor for final fields
public class CurrencyDbOperations {

    private final DataSource dataSource;
    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    private void callSql(String sql, Connection connection) throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void fillDb() throws Exception {
        if (!currencyService.fetchCurrencyList().isEmpty()) {
            return;
        }
        try (Connection connection = dataSource.getConnection()) {
            List<Currency> currencyList = CurrencyFetcher.getCurrencyList();
            for (Currency currency : currencyList) {
                currencyService.saveCurrency(currency);
            }
        }
    }

    public void updateDb() throws Exception {
        List<Currency> currencyList = CurrencyFetcher.getCurrencyList();
        for (Currency currency : currencyList) {
            currencyService.updateCurrencyByCode(currency, currency.getCode());
        }
    }
}