package com.example.checking_account_on_limit.service.impl;

import com.example.checking_account_on_limit.model.AccountRequest;
import com.example.checking_account_on_limit.model.TransactionRequest;
import com.example.checking_account_on_limit.model.entity.AccountEntity;
import com.example.checking_account_on_limit.model.entity.InfoTransactionAccountEntity;
import com.example.checking_account_on_limit.repository.AccountRepository;
import com.example.checking_account_on_limit.repository.InfoTransactionRepository;
import com.example.checking_account_on_limit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.checking_account_on_limit.constance.AccountConst.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final InfoTransactionRepository infoTransactionRepository;

    @Value("${limit.sum.default}")
    private Double limitSum;

    @Value("${limit.currency.shortname.default}")
    private String limitCurrShortname;

    @Override
    public String transactionRequest(TransactionRequest transReq) {
        AccountEntity account = accountRepository.findByAccountFrom(transReq.getAccountFrom());

        if (!isNull(account)) {
            createIntoTransaction(account, transReq);
        } else {
            LocalDateTime date = LocalDateTime.now();
            AccountEntity accEnt = new AccountEntity(transReq.getAccountFrom(), limitSum, date, limitCurrShortname, limitSum, limitSum, false);
            accountRepository.save(accEnt);

            createIntoTransaction(accEnt, transReq);
        }
        return null;
    }

    private void createIntoTransaction(AccountEntity account, TransactionRequest transReq) {
        boolean limitExceeded = true;

        if (transReq.getExpenseCategory().equals("product")) {
            account.setSumProduct(account.getSumProduct() - transReq.getSum());
            Double limitSumProduct = account.getSumProduct();
            if (limitSumProduct < 0.0) {
                limitExceeded = false;
            }

        } else if ((transReq.getExpenseCategory().equals("service"))) {
            account.setSumService(account.getSumService() - transReq.getSum());
            Double limitSumService = account.getSumService();
            if (limitSumService < 0) {
                limitExceeded = false;
            }
        }
        InfoTransactionAccountEntity infoTransEntity = new InfoTransactionAccountEntity(transReq.getDatetime(), transReq.getSum(), limitExceeded, transReq.getExpenseCategory(), transReq.getAccountFrom(), transReq.getAccountTo(), account);
        infoTransactionRepository.save(infoTransEntity);
    }

    public String setBalance(AccountRequest accountRequest) {
        if (checkFieldsValidClient(accountRequest)) {
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

    private Boolean checkFieldsValidClient(AccountRequest accountRequest) {
        return isNull(accountRequest.getAccountFrom());
    }

    private Boolean checkSettingNewLimit(AccountRequest accountRequest) {
        AccountEntity account = accountRepository.findByAccountFrom(accountRequest.getAccountFrom());
        if (isNull(account)) {
            return false;
        }
        return account.getFlagLimitSum();
    }
}
