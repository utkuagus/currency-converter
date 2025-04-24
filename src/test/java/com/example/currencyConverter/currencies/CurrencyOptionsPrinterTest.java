//package com.example.currencyConverter.currencies;
//
//import com.example.currencyConverter.currencies.entity.Currency;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class CurrencyOptionsPrinterTest {
//
//    @Test
//    public void testGetCurrencyOptions() throws Exception {
//        // Arrange: Mock CurrencyFetcher
//        CurrencyFetcher mockFetcher = mock(CurrencyFetcher.class);
//        List<Currency> mockCurrencies = Arrays.asList(
//                new Currency("USD", "US Dollar",1),
//                new Currency("EUR", "Euro",1),
//                new Currency("JPY", "Japanese Yen",1)
//        );
//
//        when(mockFetcher.getCurrencyList()).thenReturn(mockCurrencies);
//
//        // Act
//        CurrencyOptionsPrinter printer = new CurrencyOptionsPrinter(mockFetcher);
//        String result = printer.getCurrencyOptions(null);
//
//        // Assert
//        assertTrue(result.contains("value=\"USD\""));
//        assertTrue(result.contains("US Dollar"));
//        assertTrue(result.contains("value=\"EUR\""));
//        assertTrue(result.contains("Euro"));
//        assertTrue(result.contains("value=\"JPY\""));
//        assertTrue(result.contains("Japanese Yen"));
//        assertTrue(result.contains("value=\"choose\"")); // default option
//
//        System.out.println(result);
//    }
//}