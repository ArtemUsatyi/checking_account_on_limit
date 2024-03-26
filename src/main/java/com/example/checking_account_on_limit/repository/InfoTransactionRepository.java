package com.example.checking_account_on_limit.repository;

import com.example.checking_account_on_limit.model.entity.InfoTransactionAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTransactionRepository extends JpaRepository<InfoTransactionAccountEntity, Long> {
}
