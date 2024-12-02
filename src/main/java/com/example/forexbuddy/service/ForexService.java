package com.example.forexbuddy.service;

import com.example.forexbuddy.client.ForexClient;
import com.example.forexbuddy.model.dto.ExchangeRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ForexService {

  private final ForexClient forexClient;
  private final String accessKey;

  public ForexService(
      ForexClient forexClient, @Value("${clients.forex.access-key}") String accessKey) {
    this.forexClient = forexClient;
    this.accessKey = accessKey;
  }

  public List<ExchangeRateDto> getExchangeRate(String baseCurrency) {
    try {
      var forexResponseDto = forexClient.getExchangeRates(baseCurrency, accessKey);
      return forexResponseDto.rates().entrySet().stream()
          .map(
              rateEntry ->
                  new ExchangeRateDto(baseCurrency, rateEntry.getKey(), rateEntry.getValue()))
          .toList();
    } catch (Exception e) {
      log.error("Failed to fetch forex data for {} {}", baseCurrency, e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
