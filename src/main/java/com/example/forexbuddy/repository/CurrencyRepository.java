package com.example.forexbuddy.repository;

import com.example.forexbuddy.model.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  @Query("SELECT c.name FROM Currency c WHERE c.supported = true")
  List<String> getAllSupportedCurrencies();
}
