package com.example.checking_account_on_limit.service.impl;

import com.example.checking_account_on_limit.exception.JsonException;
import com.example.checking_account_on_limit.model.CurrencyPairRequest;
import com.example.checking_account_on_limit.model.entity.CurrencyEntity;
import com.example.checking_account_on_limit.repository.CurrencyRepository;
import com.example.checking_account_on_limit.service.ConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static com.example.checking_account_on_limit.constance.AccountConst.JSON_MESSAGE_ERROR;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ConversionServiceImpl implements ConversionService {

    private RestTemplate restTemplate = new RestTemplate();
    private final CurrencyRepository currencyRepository;

    @Value("${twelve.data.conversion.url}")
    private String URL;

    @Override
    public CurrencyEntity gettingExchangeRate(CurrencyPairRequest currency) {
        if (!isNull(checkFindCurrencyPair(currency))) {
            return currencyRepository.findCurrencyByNameCurrency(currency.getCurrencyPair());
        }
        String locationResponse = restTemplate.exchange(preparePair(URL, currency),
                HttpMethod.GET, new HttpEntity<>(null), String.class).getBody();
        return parseCurrency(locationResponse, currency);
    }

    private String preparePair(String url, CurrencyPairRequest currency) {
        return String.format(url, currency.getCurrencyPair());
    }

    private CurrencyEntity parseCurrency(String locationResponse, CurrencyPairRequest currency) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode json = objectMapper.readTree(locationResponse);

            String symbol = json.get("symbol").asText();
            String rate = json.get("rate").asText();
            String timestamp = json.get("timestamp").asText();
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(Long.parseLong(timestamp), 0, ZoneOffset.ofHours(3));

            if (isNull(checkFindCurrencyPairOnly(currency))) {
                return currencyRepository.save(new CurrencyEntity(symbol, Double.valueOf(rate), dateTime));
            }

            CurrencyEntity currencyEntity = currencyRepository.findCurrencyByNameCurrency(currency.getCurrencyPair());
            currencyEntity.setDateTime(dateTime);
            currencyEntity.setValueRate(Double.valueOf(rate));
            currencyRepository.save(currencyEntity);
            
            return currencyEntity;
        } catch (JsonProcessingException e) {
            throw new JsonException(JSON_MESSAGE_ERROR);
        }
    }

    private CurrencyEntity checkFindCurrencyPair(CurrencyPairRequest currency) {
        CurrencyEntity currencyEntity = currencyRepository.findCurrencyByNameCurrency(currency.getCurrencyPair());
        if (!isNull(currencyEntity)) {
            return checkDateTimeCurrencyPair(currencyEntity);
        }
        return null;
    }

    private CurrencyEntity checkDateTimeCurrencyPair(CurrencyEntity currencyEntity) {
        LocalDateTime dateTime = LocalDateTime.now();
        if (currencyEntity.getDateTime().truncatedTo(ChronoUnit.DAYS).equals(dateTime.truncatedTo(ChronoUnit.DAYS))) {
            return currencyEntity;
        }
        return null;
    }

    private CurrencyEntity checkFindCurrencyPairOnly(CurrencyPairRequest currency) {
        CurrencyEntity currencyEntity = currencyRepository.findCurrencyByNameCurrency(currency.getCurrencyPair());
        if (!isNull(currencyEntity)) {
            return currencyEntity;
        }
        return null;
    }
}
