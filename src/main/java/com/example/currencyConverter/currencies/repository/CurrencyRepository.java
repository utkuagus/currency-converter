package com.example.currencyConverter.currencies.repository;


import com.example.currencyConverter.currencies.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long>, JpaRepository<Currency, Long> {
    Optional<Currency> findByCode(String code);
}
