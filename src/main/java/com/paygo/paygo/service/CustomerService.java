package com.paygo.paygo.service;

import com.paygo.paygo.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<?> createCustomer(CustomerDto customer);
    ResponseEntity<?> fetchCustomer(String cif);
}
