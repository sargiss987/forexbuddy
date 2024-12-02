package com.example.forexbuddy.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supported_currencies")
@Getter
@Setter
public class SupportedCurrencies {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
}
