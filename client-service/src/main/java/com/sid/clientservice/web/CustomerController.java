package com.sid.clientservice.web;

import com.sid.clientservice.entities.Customer;
import com.sid.clientservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/customers")
    public Customer postCustomers(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable(name = "id") Long id){
        return customerRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long id){
        customerRepository.deleteById(id);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable(name = "id") Long id, @RequestBody Customer customer){
        customer.setId(id);
        return customerRepository.save(customer);
    }
}
