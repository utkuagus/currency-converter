package com.example.currencyConverter.db;

import com.example.currencyConverter.currencies.CurrencyFetcher;
import com.example.currencyConverter.currencies.controller.CurrencyController;
import com.example.currencyConverter.currencies.entity.Currency;
import com.example.currencyConverter.currencies.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class CurrencyDbOperations {

    private CurrencyService currencyService;

    private static void callSql(String sql, Connection connection) throws SQLException {
        var statement = connection.createStatement();
        statement.execute(sql);
    }

    public void synchDb() throws Exception {
        if(currencyService.fetchCurrencyList().isEmpty()) {
            initDb();
        }
        updateDb();
    }

    public void initDb() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:h2:file:/data/demo;AUTO_RECONNECT=TRUE;AUTO_SERVER=TRUE;PASSWORD=password;USER=sa");

        String createTableSql = "create table if not exists CURRENCY (id identity primary key, " +
                "code varchar," +
                "name varchar," +
                "usdRate numeric(50, 2));";
        callSql(createTableSql, connection);

//        for(Currency currency : currencyList) {
//            String insertTableSql = String.format(
//                    "insert into currency (code, name, usdRate) values (%s, %s, %.2f)",
//                        currency.getCode(),
//                        currency.getName(),
//                        currency.getUsdRate()
//                    );
//            callSql(insertTableSql, connection);
//        }
        List<Currency> currencyList = CurrencyFetcher.getCurrencyList();
        for(Currency currency : currencyList) {
            currencyService.saveCurrency(currency);
        }
    }

    public void updateDb() throws Exception {
        List<Currency> currencyList = CurrencyFetcher.getCurrencyList();
        for(Currency currency : currencyList) {
            currencyService.updateCurrencyByCode(currency, currency.getCode());
        }
    }
}
