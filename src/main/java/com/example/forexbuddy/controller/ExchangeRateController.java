package com.example.forexbuddy.controller;

import com.example.forexbuddy.mapper.ExchangeRateMapper;
import com.example.forexbuddy.model.dto.ExchangeRateDto;
import com.example.forexbuddy.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exchange-rate")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  public ExchangeRateController(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  @GetMapping
  public ResponseEntity<ExchangeRateDto> getExchangeRate(
      @RequestParam(name = "baseCurrency") String baseCurrency,
      @RequestParam(name = "targetCurrency") String targetCurrency) {
    return exchangeRateService
        .getExchangeRate(baseCurrency, targetCurrency)
        .map(exchangeRate -> ResponseEntity.ok(ExchangeRateMapper.toExchangeRateDto(exchangeRate)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
