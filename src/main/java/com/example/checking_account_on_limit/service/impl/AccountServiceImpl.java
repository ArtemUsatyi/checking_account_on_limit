package com.example.checking_account_on_limit.service.impl;

import com.example.checking_account_on_limit.model.AccountRequest;
import com.example.checking_account_on_limit.model.TransactionRequest;
import com.example.checking_account_on_limit.model.entity.AccountEntity;
import com.example.checking_account_on_limit.model.entity.CurrencyEntity;
import com.example.checking_account_on_limit.model.entity.InfoTransactionAccountEntity;
import com.example.checking_account_on_limit.repository.AccountRepository;
import com.example.checking_account_on_limit.repository.InfoTransactionRepository;
import com.example.checking_account_on_limit.service.AccountService;
import com.example.checking_account_on_limit.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.checking_account_on_limit.constance.ConstApplication.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final InfoTransactionRepository infoTransactionRepository;
    private final ConversionService conversionService;

    @Value("${limit.sum.default}")
    private Double limitSum;

    @Value("${limit.currency.shortname.default}")
    private String limitCurrShortname;

    @Override
    public String transactionRequest(TransactionRequest transReq) {
        if (isNull(transReq)) {
            return NON_VALID_FIELD_CLIENT;
        }
        return checkAccount(transReq);
    }

    private String createIntoTransaction(AccountEntity account, TransactionRequest transReq) {

        Double valueRate = conversionCurrency(transReq);
        boolean limitExceeded = true;

        if (transReq.getExpenseCategory().equals("product")) {
            account.setSumProduct(account.getSumProduct() - (transReq.getSum() * valueRate));
            Double limitSumProduct = account.getSumProduct();
            if (limitSumProduct < 0.0) {
                limitExceeded = false;
            }

        } else if ((transReq.getExpenseCategory().equals("service"))) {
            account.setSumService(account.getSumService() - (transReq.getSum() * valueRate));
            Double limitSumService = account.getSumService();
            if (limitSumService < 0) {
                limitExceeded = false;
            }
        }
        InfoTransactionAccountEntity infoTransEntity = new InfoTransactionAccountEntity(transReq.getDatetime(), transReq.getSum() * valueRate, limitExceeded, transReq.getExpenseCategory(), transReq.getAccountFrom(), transReq.getAccountTo(), account, transReq.getNameCurrencyTransaction());
        infoTransactionRepository.save(infoTransEntity);
        return "Транзакция успешно сохранена";
    }

    private String checkAccount(TransactionRequest transReq) {
        AccountEntity account = accountRepository.findByAccountFrom(transReq.getAccountFrom());
        if (isNull(account)) {
            LocalDateTime date = LocalDateTime.now();
            AccountEntity accEnt = new AccountEntity(transReq.getAccountFrom(), limitSum, date, limitCurrShortname, limitSum, limitSum, false);
            accountRepository.save(accEnt);
            return createIntoTransaction(accEnt, transReq);
        } else return createIntoTransaction(account, transReq);
    }

    private Double conversionCurrency(TransactionRequest transReq) {
        String nameCurrency = transReq.getNameCurrencyTransaction() + "/USD";
        CurrencyEntity currencyEntity = conversionService.gettingExchangeRate(nameCurrency);
        return currencyEntity.getValueRate();
    }

    public String setBalance(AccountRequest accountRequest) {
        if (isNull(accountRequest)) {
            return NON_VALID_FIELD_CLIENT;
        }
        if (checkSettingNewLimit(accountRequest)) {
            return USER_NOT_CHANGE_LIMIT;
        }

        LocalDateTime date = LocalDateTime.now();
        AccountEntity accountEntity = new AccountEntity(accountRequest.getAccountFrom(), accountRequest.getLimitSum(), date, accountRequest.getCurrencyShortname(), accountRequest.getLimitSum(), accountRequest.getLimitSum(), true);
        accountRepository.save(accountEntity);

        return SET_CURRENT_LIMIT;
    }

    private Boolean checkSettingNewLimit(AccountRequest accountRequest) {
        AccountEntity account = accountRepository.findByAccountFrom(accountRequest.getAccountFrom());
        if (isNull(account)) {
            return false;
        }
        return account.getFlagLimitSum();
    }
}
