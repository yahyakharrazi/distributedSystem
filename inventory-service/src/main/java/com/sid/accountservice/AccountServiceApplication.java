package com.sid.accountservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String type;
    private String etat;
    private float solde;
    private Date dateCreation;

}

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String type;
    private float montant;
    private Date date;

}

@RepositoryRestResource
interface AccountRepository extends JpaRepository<Account, Long>{
}

@RepositoryRestResource
interface OperationRepository extends JpaRepository<Operation, Long>{
}
