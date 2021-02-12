package com.sid.accountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
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
    private Long id;
    private String code;
    private String type;
    private String etat;
    private float solde;
    private Date dateCreation;
    @Transient
    private com.sid.clientservice.entities.Customer client;
    @OneToMany(mappedBy="compte")
    private List<Operation> operations;
}
