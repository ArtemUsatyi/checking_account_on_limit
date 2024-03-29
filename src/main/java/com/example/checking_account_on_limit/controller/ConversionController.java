package com.example.checking_account_on_limit.controller;

import com.example.checking_account_on_limit.model.entity.CurrencyEntity;
import com.example.checking_account_on_limit.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.checking_account_on_limit.constance.AccountConst.TEST_SETTING_CONVERSION;

@RestController
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @PostMapping(TEST_SETTING_CONVERSION)
    public CurrencyEntity conversionPair(@RequestBody String pair) {
        return conversionService.gettingExchangeRate(pair);
    }
}
