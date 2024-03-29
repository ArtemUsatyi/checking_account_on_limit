package com.example.checking_account_on_limit.repository;

import com.example.checking_account_on_limit.model.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    @Query(value = "SELECT ce FROM CurrencyEntity ce WHERE ce.nameCurrency = :nameCurrency")
    public CurrencyEntity findCurrencyByNameCurrency(String nameCurrency);
}
