package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "info_transaction_client_account")
public class InfoTransactionClientAccountEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "current balance")
    private Long currentBalance;

    @Column(name = "date_transaction")
    private LocalDate dateTransaction;

    @Column(name = "amount_transaction")
    private Long amountTransaction;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

    @OneToMany(mappedBy = "infoEntity")
    private List<ClientEntity> listClients;

    public InfoTransactionClientAccountEntity(Long currentBalance, LocalDate dateTransaction, Long amountTransaction, Boolean limitExceeded) {
        this.currentBalance = currentBalance;
        this.dateTransaction = dateTransaction;
        this.amountTransaction = amountTransaction;
        this.limitExceeded = limitExceeded;
    }
}
