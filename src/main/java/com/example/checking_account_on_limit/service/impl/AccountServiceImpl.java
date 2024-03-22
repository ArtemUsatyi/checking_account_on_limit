package com.example.checking_account_on_limit.service.impl;

import com.example.checking_account_on_limit.model.ClientModel;
import com.example.checking_account_on_limit.repository.AccountRepository;
import com.example.checking_account_on_limit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public String setBalance(ClientModel clientModel, Long balanceLimit){


        return null;
    }

}
