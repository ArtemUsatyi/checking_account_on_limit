package com.example.checking_account_on_limit.service;

import com.example.checking_account_on_limit.model.CurrencyPairRequest;
import com.example.checking_account_on_limit.model.entity.CurrencyEntity;

public interface ConversionService {
    public CurrencyEntity gettingExchangeRate(CurrencyPairRequest pair);
}
