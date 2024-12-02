package com.example.forexbuddy.mapper;

import com.example.forexbuddy.model.dto.ExchangeRateDto;
import com.example.forexbuddy.model.entities.ExchangeRate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExchangeRateMapper {

  public static ExchangeRateDto toExchangeRateDto(ExchangeRate exchangeRate) {
    return new ExchangeRateDto(
        exchangeRate.getBaseCurrency(), exchangeRate.getTargetCurrency(), exchangeRate.getRate());
  }

  public static ExchangeRate toExchangeRate(ExchangeRateDto exchangeRateDto) {
    return ExchangeRate.builder()
        .baseCurrency(exchangeRateDto.baseCurrency())
        .targetCurrency(exchangeRateDto.targetCurrency())
        .rate(exchangeRateDto.rate())
        .build();
  }
}
