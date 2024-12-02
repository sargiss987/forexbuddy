package com.example.forexbuddy.scheduler;

import com.example.forexbuddy.mapper.ExchangeRateMapper;
import com.example.forexbuddy.service.CurrencyService;
import com.example.forexbuddy.service.ExchangeRateService;
import com.example.forexbuddy.service.ForexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForexScheduler {

  private final CurrencyService currencyService;
  private final ForexService forexService;
  private final ExchangeRateService exchangeRateService;

  public ForexScheduler(
      CurrencyService currencyService,
      ForexService forexService,
      ExchangeRateService exchangeRateService) {
    this.currencyService = currencyService;
    this.forexService = forexService;
    this.exchangeRateService = exchangeRateService;
  }

  @Scheduled(fixedRateString = "${scheduler.forex.rate}")
  public void updateForexData() {
    try {
      currencyService
          .getAllSupportedCurrencies()
          .forEach(
              currency -> {
                forexService.getExchangeRate(currency).stream()
                    .map(ExchangeRateMapper::toExchangeRate)
                    .forEach(exchangeRateService::saveExchangeRate);
                log.info("Forex data is updated in database and cache for {}", currency);
              });

    } catch (Exception e) {
      log.error("Failed to update forex data {}", e.getMessage());
    }
  }
}
