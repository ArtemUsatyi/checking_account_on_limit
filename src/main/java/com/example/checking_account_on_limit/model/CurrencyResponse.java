package com.example.checking_account_on_limit.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrencyResponse {
    private String currencyFrom;
    private String currencyTo;
    private Double valueRate;
    private LocalDateTime dateTime;
}
