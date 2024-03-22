package com.example.checking_account_on_limit.controller;

import com.example.checking_account_on_limit.model.AccountModel;
import com.example.checking_account_on_limit.model.ClientModel;
import com.example.checking_account_on_limit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.checking_account_on_limit.constance.AccountConst.REGISTRATION_TRANSACTION;
import static com.example.checking_account_on_limit.constance.AccountConst.SETTING_BALANCE_LIMIT;

@RestController
@RequiredArgsConstructor
public class AccountLimitController {

    private final AccountService accountService;

    @PostMapping(REGISTRATION_TRANSACTION)
    public void registrationTransaction(@RequestBody AccountModel accountModel) {

    }

    @PostMapping(SETTING_BALANCE_LIMIT)
    public String settingBalance(@RequestBody ClientModel clientModel, @RequestParam Long balanceLimit) {

        return accountService.setBalance(clientModel, balanceLimit);
    }
}
