package com.example.checking_account_on_limit.repository;

import com.example.checking_account_on_limit.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    public AccountEntity findByAccountFrom(Long accountFrom);
}
