package com.example.forexbuddy.client;

import com.example.forexbuddy.model.dto.ForexResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "forexClient", url = "${clients.forex.base-url}")
public interface ForexClient {

  String BASE_CURRENCY = "base";
  String ACCESS_KEY = "access_key";

  @GetMapping("/latest")
  ForexResponseDto getExchangeRates(
      @RequestParam(BASE_CURRENCY) String baseCurrency, @RequestParam(ACCESS_KEY) String accessKey);
}
