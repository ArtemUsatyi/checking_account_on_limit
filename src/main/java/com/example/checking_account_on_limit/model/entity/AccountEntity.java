package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "account_application_table")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "type_currency")
    private String typeCurrency;

    @Column(name = "limit_balance")
    private Long limitBalance;

    @Column(name = "current balance")
    private Long currentBalance;

    @Column(name = "date_transaction")
    private LocalDate dateTransaction;

    @Column(name = "amount_transaction")
    private Long amountTransaction;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    @OneToOne(mappedBy = "accountEntity")
    private ClientEntity clientEntity;

    public AccountEntity(LocalDate startDate, String typeCurrency, Long limitBalance, Long currentBalance, LocalDate dateTransaction, Long amountTransaction, Boolean limitExceeded) {
        this.startDate = startDate;
        this.typeCurrency = typeCurrency;
        this.limitBalance = limitBalance;
        this.currentBalance = currentBalance;
        this.dateTransaction = dateTransaction;
        this.amountTransaction = amountTransaction;
        this.limitExceeded = limitExceeded;
    }
}
