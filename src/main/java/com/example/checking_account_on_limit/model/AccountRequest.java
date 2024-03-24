package com.example.checking_account_on_limit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private LocalDate startDate;
    private String typeUnit;
    private Long limitBalance;
}
