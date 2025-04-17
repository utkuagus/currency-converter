package com.example.currencyConverter.currencies;

import com.example.currencyConverter.currencies.models.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Objects;

public class CurrencyOptionsPrinter {

    private CurrencyFetcher currencyFetcher;

    public CurrencyOptionsPrinter(CurrencyFetcher currencyFetcher) {
        this.currencyFetcher = currencyFetcher;
    }

    public String getCurrencyOptions(String selectedCode) throws Exception {
        List<Currency> currencyList = currencyFetcher.getCurrencyList();

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

    public String fillCurrencyCodes(String htmlText) throws Exception {
        Document doc = Jsoup.parse(htmlText);

        Element fromCurrencySelect = doc.select("select").first();
        clearCurrencyCodes(fromCurrencySelect);
        addCurrencyCodes(fromCurrencySelect);


        return doc.html();
    }

    public void clearCurrencyCodes(Element select) {
        if (select != null) {
            select.empty();
        }
    }

    public void addCurrencyCodes(Element select) throws Exception {
        if(select == null) {
            return;
        }
        select.appendText(getCurrencyOptions(null));
    }
}
