package com.example.checking_account_on_limit.service;

import com.example.checking_account_on_limit.model.AccountRequest;
import com.example.checking_account_on_limit.model.TransactionRequest;

public interface AccountService {

    public String transactionRequest(TransactionRequest transactionRequest);
    public String setBalance(AccountRequest accountRequest);
}
