package com.example.forexbuddy.repository;

import com.example.forexbuddy.model.entities.SupportedCurrencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportedCurrenciesRepository extends JpaRepository<SupportedCurrencies, Long> {

  boolean existsByName(String name);
}
