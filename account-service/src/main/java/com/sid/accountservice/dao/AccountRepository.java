package com.sid.accountservice.dao;

import com.sid.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByClientId(Long idClient);
}
