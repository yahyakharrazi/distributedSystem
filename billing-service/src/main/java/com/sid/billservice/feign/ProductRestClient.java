package com.sid.billservice.feign;


import com.ylizma.billservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {

    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable Long id);
}
