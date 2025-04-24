package com.example.currencyConverter.currencies.service;

import com.example.currencyConverter.currencies.entity.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyFetcher {

    private static final List<Currency> currencyList = new ArrayList<>();

    public static List<Currency> getCurrencyList() throws Exception {
        fetchCurrencyList();
        return currencyList;
    }

    public static void fetchCurrencyList() throws Exception {
        String url = "https://www.x-rates.com/table/?from=USD&amount=1";

        Document jsoupDoc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10_000)
                .get();

        Element ratesTable = jsoupDoc.select(".ratesTable").get(1);
        Elements tbody = ratesTable.select("tbody");


        for (Element row : tbody.select("tr")) {
            Elements columns = row.select("td");

            if (columns.size() >= 2) {

                String currencyName = columns.get(0).text();
                String exchangeRate = columns.get(1).select("a").text();
                String currencyLink = columns.get(1).select("a").attr("href");
                String currencyCode = currencyLink.substring(currencyLink.length() - 3);

                currencyList.add(new Currency(currencyCode, currencyName, Double.parseDouble(exchangeRate)));
            }
        }
        currencyList.add(new Currency("USD", "US Dollar", 1.0));

        currencyList.forEach(System.out::println);
    }

}
