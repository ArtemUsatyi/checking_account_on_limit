package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "info_transaction_client_account")
public class InfoTransactionAccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_transaction")
    private LocalDateTime dateTransaction; // дата транзакции

    @Column(name = "sum")
    private Double sum; // сумма транзакции

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded; // флаг транзакции

    @Column(name = "expense_category")
    private String expenseCategory; // категория расходов

    @Column(name = "account_from")
    private Long accountFrom; // банковский счет клиента

    @Column(name = "account_to")
    private Long accountTo; // банковский счет контрагента

    @Column(name = "currency_shortname")
    private String currencyShortname; // валюта счета

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity accountEntity;

    public InfoTransactionAccountEntity(LocalDateTime dateTransaction, Double sum, Boolean limitExceeded, String expenseCategory, Long accountFrom, Long accountTo, AccountEntity accountEntity, String currencyShortname) {
        this.dateTransaction = dateTransaction;
        this.sum = sum;
        this.limitExceeded = limitExceeded;
        this.expenseCategory = expenseCategory;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.accountEntity = accountEntity;
        this.currencyShortname=currencyShortname;
    }
}
