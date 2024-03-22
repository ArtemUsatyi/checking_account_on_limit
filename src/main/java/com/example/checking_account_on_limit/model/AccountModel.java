package com.example.checking_account_on_limit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {

    private LocalDate startDate;
    private String typeCurrency;
    private Long limitBalance;
    private Long currentBalance;
    private LocalDate dateTransaction;
    private Long amountTransaction;
}
