package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "account_application")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_from")
    private Long accountFrom; // банковский счет клиента

    @Column(name = "limit_sum_account")
    private Double limitSum; // лимит по сумме

    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime; // установление даты

    @Column(name = "currency_shortname")
    private String currencyShortname; // валюта счета

    @Column(name = "sum_product")
    private Double sumProduct; // сумма по product

    @Column(name = "sum_service")
    private Double sumService; // сумма по service

    @OneToMany(mappedBy = "accountEntity")
    private List<InfoTransactionAccountEntity> listInfo;

    public AccountEntity(Long accountFrom, Double limitSum, LocalDateTime limitDatetime, String currencyShortname, Double sumProduct, Double sumService) {
        this.accountFrom = accountFrom;
        this.limitSum = limitSum;
        this.limitDatetime = limitDatetime;
        this.currencyShortname = currencyShortname;
        this.sumProduct = sumProduct;
        this.sumService = sumService;
    }
}
