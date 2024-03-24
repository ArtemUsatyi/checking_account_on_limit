package com.example.checking_account_on_limit.repository;

import com.example.checking_account_on_limit.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public ClientEntity findByNameClient(String name);
}
