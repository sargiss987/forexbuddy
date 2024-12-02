package com.example.forexbuddy.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExchangeRateKeyGenerator {

  public static String generate(String baseCurrency, String targetCurrency) {
    return String.format("%s_%s", baseCurrency, targetCurrency);
  }
}
