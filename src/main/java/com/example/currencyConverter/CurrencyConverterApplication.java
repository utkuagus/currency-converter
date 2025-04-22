package com.example.currencyConverter;

import com.example.currencyConverter.currencies.CurrencyFetcher;
import com.example.currencyConverter.currencies.controller.CurrencyController;
import com.example.currencyConverter.currencies.repository.CurrencyRepository;
import com.example.currencyConverter.currencies.service.CurrencyService;
import com.example.currencyConverter.currencies.service.CurrencyServiceImpl;
import com.example.currencyConverter.db.CurrencyDbOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CurrencyConverterApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(CurrencyConverterApplication.class, args);
		CurrencyService currencyService = applicationContext.getBean(CurrencyService.class);
		CurrencyDbOperations currencyDbOperations = new CurrencyDbOperations(currencyService);
		currencyDbOperations.synchDb();
	}

}
