package com.sid.accountservice.controllers;

import com.sid.accountservice.dao.AccountRepository;
import com.sid.accountservice.entities.Account;
import com.sid.accountservice.feign.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/comptes")
public class AccountController {
    @Autowired
    public ServiceCustomer serviceCustomer;

    @Autowired public AccountRepository compteRepository;

    @GetMapping("/customer/{id}")
    public Account getAccount(@PathVariable Long id) {
        com.sid.clientservice.entities.Customer customer = serviceCustomer.getCustomerById(id);
        Account compte = compteRepository.findByCustomerId(id);
        compte.setCustomer(customer);
        return compte;
    }

    @GetMapping("/customer/{id}/change-etat")
    public Boolean compteStatu(@PathVariable Long id) {
        Account compte = compteRepository.findByCustomerId(id);
        compte.setEtat(!compte.isEtat());
        Account c = compteRepository.save(compte);
        return c.isEtat();
    }

}

