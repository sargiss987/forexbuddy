package com.example.forexbuddy.mapper;

import com.example.forexbuddy.model.dto.CurrencyDto;
import com.example.forexbuddy.model.entities.Currency;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrencyMapper {

  public static CurrencyDto toCurrencyDto(Currency currency) {
    return new CurrencyDto(currency.getName(), currency.getSupported());
  }

  public static Currency toCurrency(CurrencyDto currencyDto) {
    return Currency.builder().name(currencyDto.name()).build();
  }
}
