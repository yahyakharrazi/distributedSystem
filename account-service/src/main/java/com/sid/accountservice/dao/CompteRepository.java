package com.sid.accountservice.dao;

import com.sid.accountservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface CompteRepository extends JpaRepository<Compte, Long> {
    public Compte findByUsername(String username);
    //    @Query(value = "select id from compte where username=?1 and password=?2")
    public Compte findByUsernameAndPassword(String username, String password);
}
