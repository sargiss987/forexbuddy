package com.example.forexbuddy.repository;

import com.example.forexbuddy.model.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

  @Query(
      """
        SELECT er FROM ExchangeRate er
        WHERE er.baseCurrency = :baseCurrency AND er.targetCurrency = :targetCurrency
        ORDER BY er.timestamp DESC
      """)
  Optional<ExchangeRate> getExchangeRate(
      @Param("baseCurrency") String baseCurrency, @Param("targetCurrency") String targetCurrency);
}
