package com.example.checking_account_on_limit.service.impl;

import com.example.checking_account_on_limit.model.ClientModel;
import com.example.checking_account_on_limit.model.entity.AccountEntity;
import com.example.checking_account_on_limit.model.entity.ClientEntity;
import com.example.checking_account_on_limit.repository.AccountRepository;
import com.example.checking_account_on_limit.repository.ClientRepository;
import com.example.checking_account_on_limit.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.checking_account_on_limit.constance.AccountConst.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public String setBalance(ClientModel clientModel, String typeUnit, Long balanceLimit) {
        if (checkFieldsValidClient(clientModel)) {
            return NON_VALID_FIELD_CLIENT;
        }

        if (checkValidNameClient(clientModel)) {
            return USER_NOT_CHANGE_LIMIT;
        }

        LocalDate currentDate = LocalDate.now();
        AccountEntity account = accountRepository.save(new AccountEntity(currentDate, typeUnit, balanceLimit));

        clientRepository.save(new ClientEntity(clientModel.getNameClient(), account));

        return SET_CURRENT_LIMIT;
    }

    private Boolean checkFieldsValidClient(ClientModel clientModel) {
        return isNull(clientModel.getNameClient());
    }

    private Boolean checkValidNameClient(ClientModel clientModel) {
        return isNull(clientRepository.findByNameClient(new ClientEntity(clientModel.getNameClient()).getNameClient()));
    }

//    private Boolean checkValidLimitBalance(ClientModel clientModel) {
//        if (checkValidNameClient(clientModel)) {
//            return true;
//        }
//
//        AccountEntity accountEntity = new ClientEntity(clientModel.getNameClient()).getAccountEntity();
//        LocalDate currentStartDate = accountEntity.getStartDate();
//
//        LocalDate nowDate = LocalDate.now().minusDays(20);
//
//        return nowDate.isBefore(currentStartDate);
//    }

}
