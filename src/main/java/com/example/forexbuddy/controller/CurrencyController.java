package com.example.forexbuddy.controller;

import com.example.forexbuddy.mapper.CurrencyMapper;
import com.example.forexbuddy.model.dto.CurrencyDto;
import com.example.forexbuddy.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

  private final CurrencyService currencyService;

  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping
  public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
    return ResponseEntity.ok(
        currencyService.getAllCurrencies().stream().map(CurrencyMapper::toCurrencyDto).toList());
  }

  @PostMapping
  public ResponseEntity<CurrencyDto> addNewCurrency(@RequestBody CurrencyDto currency) {
    return ResponseEntity.ok(
        CurrencyMapper.toCurrencyDto(
            currencyService.addNewCurrency(CurrencyMapper.toCurrency(currency))));
  }
}
