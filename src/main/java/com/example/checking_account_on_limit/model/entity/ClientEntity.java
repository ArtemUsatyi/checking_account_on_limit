package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "client_application")
public class ClientEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_client")
    private String nameClient;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "info_transaction_id")
    private InfoTransactionClientAccountEntity infoEntity;

    public ClientEntity(String nameClient, AccountEntity accountEntity) {
        this.nameClient = nameClient;
        this.accountEntity = accountEntity;
    }

    public ClientEntity(String nameClient) {
        this.nameClient = nameClient;
    }
}
