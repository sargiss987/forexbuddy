package com.example.forexbuddy.service;

import com.example.forexbuddy.model.entities.Currency;
import com.example.forexbuddy.repository.CurrencyRepository;
import com.example.forexbuddy.repository.SupportedCurrenciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

  private final CurrencyRepository currencyRepository;
  private final SupportedCurrenciesRepository supportedCurrenciesRepository;

  public CurrencyService(
      CurrencyRepository currencyRepository,
      SupportedCurrenciesRepository supportedCurrenciesRepository) {
    this.currencyRepository = currencyRepository;
    this.supportedCurrenciesRepository = supportedCurrenciesRepository;
  }

  public List<Currency> getAllCurrencies() {
    return currencyRepository.findAll();
  }

  public List<String> getAllSupportedCurrencies() {
    return currencyRepository.getAllSupportedCurrencies();
  }

  public Currency addNewCurrency(Currency currency) {
    currency.setSupported(supportedCurrenciesRepository.existsByName(currency.getName()));
    return currencyRepository.save(currency);
  }
}
