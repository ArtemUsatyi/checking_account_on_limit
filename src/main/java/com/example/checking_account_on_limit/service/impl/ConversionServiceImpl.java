package com.example.checking_account_on_limit.service.impl;

import com.example.checking_account_on_limit.exception.JsonException;
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
    public CurrencyEntity gettingExchangeRate(String nameCurrency) {
        if (!isNull(checkFindCurrencyPair(nameCurrency))) {
            return currencyRepository.findCurrencyByNameCurrency(nameCurrency);
        }
        String locationResponse = restTemplate.exchange(preparePair(URL, nameCurrency),
                HttpMethod.GET, new HttpEntity<>(null), java.lang.String.class).getBody();
        return parseCurrency(locationResponse, nameCurrency);
    }

    private java.lang.String preparePair(String url, String nameCurrency) {
        return java.lang.String.format(url, nameCurrency);
    }

    private CurrencyEntity parseCurrency(String locationResponse, String nameCurrency) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode json = objectMapper.readTree(locationResponse);

            java.lang.String symbol = json.get("symbol").asText();
            java.lang.String rate = json.get("rate").asText();
            java.lang.String timestamp = json.get("timestamp").asText();
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(Long.parseLong(timestamp), 0, ZoneOffset.ofHours(3));

            if (isNull(checkFindCurrencyPairOnly(nameCurrency))) {
                return currencyRepository.save(new CurrencyEntity(symbol, Double.valueOf(rate), dateTime));
            }

            CurrencyEntity currencyEntity = currencyRepository.findCurrencyByNameCurrency(nameCurrency);
            currencyEntity.setDateTime(dateTime);
            currencyEntity.setValueRate(Double.valueOf(rate));
            currencyRepository.save(currencyEntity);

            return currencyEntity;
        } catch (JsonProcessingException e) {
            throw new JsonException(JSON_MESSAGE_ERROR);
        }
    }

    private CurrencyEntity checkFindCurrencyPair(String nameCurrency) {
        CurrencyEntity currencyEntity = currencyRepository.findCurrencyByNameCurrency(nameCurrency);
        if (isNull(currencyEntity)) {
            return null;
        }
        return checkDateTimeCurrencyPair(currencyEntity);
    }

    private CurrencyEntity checkDateTimeCurrencyPair(CurrencyEntity currencyEntity) {
        LocalDateTime dateTime = LocalDateTime.now();
        if (currencyEntity.getDateTime().truncatedTo(ChronoUnit.DAYS).equals(dateTime.truncatedTo(ChronoUnit.DAYS))) {
            return currencyEntity;
        }
        return null;
    }

    private CurrencyEntity checkFindCurrencyPairOnly(String nameCurrency) {
        CurrencyEntity currencyEntity = currencyRepository.findCurrencyByNameCurrency(nameCurrency);
        if (isNull(currencyEntity)) {
            return null;
        }
        return currencyEntity;
    }
}
