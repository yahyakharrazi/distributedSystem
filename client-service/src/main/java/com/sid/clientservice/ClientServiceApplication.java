package com.sid.clientservice;

import com.sid.clientservice.entities.Customer;
import com.sid.clientservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(new Customer(null, "Mohamed","med@gmail.com"));
            customerRepository.save(new Customer(null, "Yassine","Yassine@gmail.com"));
            customerRepository.save(new Customer(null, "Salim","salim@gmail.com"));
            customerRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
        };
    }
}
