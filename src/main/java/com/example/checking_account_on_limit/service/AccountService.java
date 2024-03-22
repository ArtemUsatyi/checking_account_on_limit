package com.example.checking_account_on_limit.service;

import com.example.checking_account_on_limit.model.ClientModel;

public interface AccountService {
    public String setBalance(ClientModel clientModel, Long balanceLimit);
}
