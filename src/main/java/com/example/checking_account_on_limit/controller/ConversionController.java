package com.example.checking_account_on_limit.controller;

import com.example.checking_account_on_limit.model.CurrencyPairInfo;
import com.example.checking_account_on_limit.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @PostMapping("/test")
    public String conversionPair(@RequestBody CurrencyPairInfo pair) {
        return conversionService.gettingExchangeRate(pair);
    }
}
