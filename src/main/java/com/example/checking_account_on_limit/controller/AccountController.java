package com.example.checking_account_on_limit.controller;

import com.example.checking_account_on_limit.model.AccountRequest;
import com.example.checking_account_on_limit.model.TransactionRequest;
import com.example.checking_account_on_limit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.checking_account_on_limit.constance.AccountConst.REGISTRATION_TRANSACTION;
import static com.example.checking_account_on_limit.constance.AccountConst.SETTING_BALANCE_LIMIT;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(REGISTRATION_TRANSACTION)
    public String registrationTransaction(@RequestBody TransactionRequest transactionRequest) {
        return accountService.transactionRequest(transactionRequest);
    }

    @PostMapping(SETTING_BALANCE_LIMIT)
    public String settingBalance(@RequestBody AccountRequest accountRequest) {

        return accountService.setBalance(accountRequest);
    }
}
