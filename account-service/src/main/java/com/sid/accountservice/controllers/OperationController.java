package com.sid.accountservice.controllers;

import com.sid.accountservice.dao.AccountRepository;
import com.sid.accountservice.dao.OperationRepository;
import com.sid.accountservice.entities.Account;
import com.sid.accountservice.entities.Operation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired public ServiceAccount serviceCustomer;
    @Autowired public AccountRepository compteRepository;
    @Autowired public OperationRepository operationRepository;


    @PostMapping("/versement")
    public Operation versement(@RequestBody OperationFrom form) {
        com.sid.clientservice.entities.Customer client = serviceCustomer.getCustomerById(form.getCustomerId());
        Account compte = compteRepository.findByCustomerId(client.getCode());
        Operation operation = new Operation(null, LocalDateTime.now(), form.getMountant(), "DEBIT", compte);
        compte.setSolde(compte.getSolde() + form.getMountant());
        compteRepository.save(compte);
        return operationRepository.save(operation);
    }

    @PostMapping("/retrait")
    public Operation retrait(@RequestBody OperationFrom form) {
        com.sid.clientservice.entities.Customer client = serviceCustomer.getCustomerById(form.getCustomerId());
        Account compte = compteRepository.findByCustomerId(client.getCode());
        Operation operation = new Operation(null, LocalDateTime.now(), form.getMountant(), "CREDIT", compte);

        compte.setSolde(compte.getSolde() - form.getMountant());
        compteRepository.save(compte);
        return operationRepository.save(operation);
    }


    @PostMapping("/virement")
    public String virement(@RequestBody CFrom form) {
        com.sid.clientservice.entities.Customer clientFrom = serviceCustomer.getCustomerById(form.getCustomerIdFrom());
        com.sid.clientservice.entities.Customer clientTo = serviceCustomer.getCustomerById(form.getCustomerIdTo());
        Account compteFrom = compteRepository.findByCustomerId(clientFrom.getCode());
        Account compteTo = compteRepository.findByCustomerId(clientTo.getCode());
        Operation operationCREDIT = new Operation(null, LocalDateTime.now(), form.getMountant(), "CREDIT", compteFrom);
        Operation operationDEBIT = new Operation(null, LocalDateTime.now(), form.getMountant(), "DEBIT", compteTo);
        compteFrom.setSolde(compteFrom.getSolde() - form.getMountant());
        compteTo.setSolde(compteTo.getSolde() + form.getMountant());
        operationRepository.save(operationCREDIT);
        operationRepository.save(operationDEBIT);
        return "Done";
    }

    @GetMapping("/{id}")
    public Page<Operation> findAllOperation(@PathVariable(name = "id") Long clientId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ) {
        Account compte = compteRepository.findByCustomerId(clientId);
        return operationRepository.findOperationByAccount(compte, PageRequest.of(page, size));
    }
}

@Data
class OperationFrom {
    private Long clientId;
    private double mountant;
}


@Data
class CFrom {
    private Long clientIdFrom;
    private Long clientIdTo;
    private double mountant;
}