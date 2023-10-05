package com.paygo.paygo.controller;

import com.paygo.paygo.dto.CustomerDto;
import com.paygo.paygo.impl.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ops/")
public class OperationController {

    @Autowired
    private CustomerServiceImp customerService;


    @PostMapping("customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customer){
        return customerService.createCustomer(customer);
    }
}
