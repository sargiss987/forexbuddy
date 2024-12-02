package com.example.forexbuddy.service;

import com.example.forexbuddy.model.entities.ExchangeRate;
import com.example.forexbuddy.repository.ExchangeRateRepository;
import com.example.forexbuddy.util.ExchangeRateKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ExchangeRateService {

  private static final Map<String, ExchangeRate> exchangeRateCache = new ConcurrentHashMap<>();

  private final ExchangeRateRepository exchangeRateRepository;

  public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
    this.exchangeRateRepository = exchangeRateRepository;
  }

  public void saveExchangeRate(ExchangeRate exchangeRate) {
    exchangeRateCache.put(
        ExchangeRateKeyGenerator.generate(
            exchangeRate.getBaseCurrency(), exchangeRate.getTargetCurrency()),
        exchangeRate);
    exchangeRateRepository.save(exchangeRate);
  }

  public Optional<ExchangeRate> getExchangeRate(String baseCurrency, String targetCurrency) {
    String key = ExchangeRateKeyGenerator.generate(baseCurrency, targetCurrency);
    if (exchangeRateCache.containsKey(key)) {
      log.info("Cache hit for exchange rate: base={}, target={}", baseCurrency, targetCurrency);
      return Optional.of(exchangeRateCache.get(key));
    }
    var exchangeRate = exchangeRateRepository.getExchangeRate(baseCurrency, targetCurrency);
    exchangeRate.ifPresent(rate -> exchangeRateCache.put(key, rate));
    return exchangeRate;
  }
}
