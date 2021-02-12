package com.sid.accountservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sid.accountservice.entities.*;
@FeignClient(name="ACCOUNT-SERVICE")
public interface ServiceCustomer {
    @RequestMapping(method = RequestMethod.GET, value="/customers/{id}", produces = "application/json")
    com.sid.clientservice.entities.Customer getCustomerById(@PathVariable("id") Long id);
}