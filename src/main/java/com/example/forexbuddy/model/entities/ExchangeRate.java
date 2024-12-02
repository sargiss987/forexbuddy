package com.example.forexbuddy.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String baseCurrency;

  private String targetCurrency;

  private BigDecimal rate;

  @CreationTimestamp private LocalDateTime timestamp;
}
