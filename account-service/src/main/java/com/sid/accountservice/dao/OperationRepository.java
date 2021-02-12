package com.sid.accountservice.dao;

import com.sid.accountservice.entities.Account;
import com.sid.accountservice.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface OperationRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findOperationByCompte(Account account, Pageable pageable);
}
