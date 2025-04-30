package com.example.currencyConverter.servlet;

import com.example.currencyConverter.currencies.service.CurrencyConverter;
import com.example.currencyConverter.currencies.service.CurrencyOptionsPrinter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

@WebServlet(name = "FormServlet", urlPatterns = "/calculateServlet")
public class FormServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(FormServlet.class);
    @Autowired
    private CurrencyOptionsPrinter currencyOptionsPrinter;
    @Autowired
    private CurrencyConverter currencyConverter;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
            if (currencyConverter == null || currencyOptionsPrinter == null) {
                logger.error("Spring beans are not injected correctly");
            } else {
                logger.info("Spring beans injected successfully");
            }
        } catch (Exception e) {
            logger.error("Error initializing Spring context: ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Received request to calculate conversion");

        String amount = request.getParameter("amount");
        String fromCurrency = request.getParameter("fromCurrency");
        String toCurrency = request.getParameter("toCurrency");

        Double result = null;
        try {
            if (amount != null) {
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
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
    }
}