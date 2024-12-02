package com.example.forexbuddy.model.dto;

import java.math.BigDecimal;

public record ExchangeRateDto(String baseCurrency, String targetCurrency, BigDecimal rate) {}
