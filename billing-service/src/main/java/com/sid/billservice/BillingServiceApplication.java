package com.sid.billservice;

import com.sid.billservice.feign.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(com.sid.billservice.feign.ProductRestClient client){
        return args -> {
            System.out.println(client.findProductById(2L));
            System.out.println("hiii");
        };
    }
}
