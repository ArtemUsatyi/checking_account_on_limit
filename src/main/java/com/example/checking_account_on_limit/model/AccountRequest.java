package com.example.checking_account_on_limit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private Long accountFrom;
    private String currencyShortname;
    private Double limitSum;
}
