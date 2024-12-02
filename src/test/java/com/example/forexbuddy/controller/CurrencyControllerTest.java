package com.example.forexbuddy.controller;

import com.example.forexbuddy.model.entities.Currency;
import com.example.forexbuddy.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private CurrencyService currencyService;

  @Test
  void testGetAllCurrencies() throws Exception {
    var currency1 = new Currency(1L, "EUR", true);
    var currency2 = new Currency(2L, "USD", false);
    var currencies = List.of(currency1, currency2);

    when(currencyService.getAllCurrencies()).thenReturn(currencies);

    mockMvc
        .perform(get("/api/v1/currencies"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("EUR"))
        .andExpect(jsonPath("$[0].supported").value(true))
        .andExpect(jsonPath("$[1].name").value("USD"))
        .andExpect(jsonPath("$[1].supported").value(false));

    verify(currencyService, times(1)).getAllCurrencies();
  }

  @Test
  void testAddNewCurrency() throws Exception {
    var currency = new Currency(1L, "EUR", true);

    when(currencyService.addNewCurrency(any())).thenReturn(currency);

    mockMvc
        .perform(
            post("/api/v1/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"EUR\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("EUR"))
        .andExpect(jsonPath("$.supported").value(true));

    verify(currencyService, times(1)).addNewCurrency(any());
  }
}
