package com.example.forexbuddy.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@Testcontainers
@SpringBootTest
@TestPropertySource(
    properties = {"clients.forex.access-key=testAccessKey", "app.scheduling.enable=false"})
class ForexClientTest {

  @Container
  static WireMockContainer wireMockContainer =
      new WireMockContainer("wiremock/wiremock:3.9.2-alpine")
          .withMappingFromResource(
              "forex_success", ForexClientTest.class, "forex_success_response_mapping.json")
          .withMappingFromResource(
              "forex_error", ForexClientTest.class, "forex_error_response_mapping.json");

  @DynamicPropertySource
  static void configBaseUrl(DynamicPropertyRegistry registry) {
    registry.add("clients.forex.base-url", () -> wireMockContainer.getBaseUrl() + "/v1");
  }

  @Autowired private ForexClient forexClient;

  @Autowired private ObjectMapper objectMapper;

  @BeforeAll
  static void setup() {
    wireMockContainer.start();
  }

  @AfterAll
  static void tearDown() {
    wireMockContainer.stop();
  }

  @Test
  void testGetExchangeRates_successfulResponse() throws Exception {

    String expected =
        IOUtils.toString(
            Objects.requireNonNull(
                this.getClass().getResourceAsStream("forex_success_response.json")),
            StandardCharsets.UTF_8);

    String actual =
        objectMapper.writeValueAsString(forexClient.getExchangeRates("EUR", "valid-access-key"));

    assertEquals(expected, actual, true);
  }

  @Test
  void testGetExchangeRates_errorResponse() {

    assertThrows(
        FeignException.Unauthorized.class,
        () -> forexClient.getExchangeRates("EUR", "invalid-access-key"));
  }
}
