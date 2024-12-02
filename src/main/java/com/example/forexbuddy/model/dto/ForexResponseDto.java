package com.example.forexbuddy.model.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ForexResponseDto(String base, Map<String, BigDecimal> rates) {}
