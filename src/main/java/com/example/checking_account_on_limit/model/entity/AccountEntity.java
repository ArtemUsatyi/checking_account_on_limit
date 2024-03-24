package com.example.checking_account_on_limit.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "account_application")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "type_unit")
    private String typeUnit;

    @Column(name = "limit_account")
    private Long limitAccount;

    @OneToOne(mappedBy = "accountEntity")
    private ClientEntity clientEntity;


    public AccountEntity(LocalDate startDate, String typeUnit, Long limitAccount) {
        this.startDate = startDate;
        this.typeUnit = typeUnit;
        this.limitAccount = limitAccount;
    }
}
