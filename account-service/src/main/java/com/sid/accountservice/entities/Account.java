package com.sid.accountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private double solde;
    @CreationTimestamp
    private LocalDateTime dateCreation;
    private String type;
    private boolean etat;
    private Long clientId;
    @Transient
    private com.sid.clientservice.entities.Customer client;
    @OneToMany(mappedBy="account")
    private List<Operation> operations;
}
