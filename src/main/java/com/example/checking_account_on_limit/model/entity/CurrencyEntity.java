package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "currency_application")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_currency")
    private String nameCurrency;

    @Column(name = "value_rate")
    private Double valueRate;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    public CurrencyEntity(String nameCurrency, Double valueRate, LocalDateTime dateTime) {
        this.nameCurrency = nameCurrency;
        this.valueRate = valueRate;
        this.dateTime = dateTime;
    }
}
