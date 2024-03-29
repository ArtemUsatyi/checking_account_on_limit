package com.example.checking_account_on_limit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Long accountFrom;
    private Long accountTo;
    private String nameCurrencyTransaction;
    private Double sum;
    private String expenseCategory;
    private LocalDateTime datetime;
}
