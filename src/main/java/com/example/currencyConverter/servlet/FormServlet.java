package com.example.currencyConverter.servlet;

import com.example.currencyConverter.currencies.CurrencyConverter;
import com.example.currencyConverter.currencies.CurrencyFetcher;
import com.example.currencyConverter.currencies.CurrencyOptionsPrinter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FormServlet", urlPatterns = "/calculateServlet")
public class FormServlet extends HttpServlet {

    private CurrencyOptionsPrinter currencyOptionsPrinter;
    private CurrencyConverter currencyConverter;


    @Override
    public void init() throws ServletException {
        CurrencyFetcher currencyFetcher = new CurrencyFetcher();
        currencyOptionsPrinter = new CurrencyOptionsPrinter(currencyFetcher);
        currencyConverter = new CurrencyConverter(currencyFetcher);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
      throws ServletException, IOException {

        System.out.println("dopost");

        String amount = request.getParameter("amount");
        String fromCurrency = request.getParameter("fromCurrency");
        String toCurrency = request.getParameter("toCurrency");

        Double result = null;
        try {
            if(amount != null) {
                result = currencyConverter.convertCurrency(Double.valueOf(amount), fromCurrency, toCurrency);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {

            request.setAttribute("fromCurrencyOptions", currencyOptionsPrinter.getCurrencyOptions(fromCurrency));
            request.setAttribute("toCurrencyOptions", currencyOptionsPrinter.getCurrencyOptions(toCurrency));
            request.setAttribute("amount", amount);
            request.setAttribute("result", result);
            response.setHeader("Test", "Success");

            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "error");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }
}