package com.sid.accountservice;

import com.sid.accountservice.dao.AccountRepository;
import com.sid.accountservice.entities.Account;
import com.sid.accountservice.entities.Operation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository){
        return args -> {
            accountRepository.save(new Account(null,"account1","COURANT","ACTIVE",100,Date.from(Instant.now())));
            accountRepository.save(new Account(null,"account2","EPARGNE","BLOQUE",100,Date.from(Instant.now())));
            accountRepository.save(new Account(null,"account3","COURANT","BLOQUE",100,Date.from(Instant.now())));
            accountRepository.findAll().forEach(product -> {
              System.out.println(product.getCode());
          });
        };
    }
}

