package com.paygo.paygo.controller;

import com.paygo.paygo.dto.AccountDto;
import com.paygo.paygo.dto.AccountOfficerDto;
import com.paygo.paygo.dto.CustomerDto;
import com.paygo.paygo.entity.AccountOfficer;
import com.paygo.paygo.impl.AccountServiceImpl;
import com.paygo.paygo.impl.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ops/")
public class OperationController {

    @Autowired
    private CustomerServiceImp customerService;

    @Autowired
    private AccountServiceImpl accountService;


    @PostMapping("customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping("customer")
    public ResponseEntity<?> fetchCustomer(@RequestParam(name = "cif") String cif){
        return customerService.fetchCustomer(cif);
    }

    @PostMapping("account/officer")
    public ResponseEntity<?> createAccountOfficer(@RequestBody AccountOfficerDto accountOfficerDto){
        return accountService.createAccountOfficer(accountOfficerDto);
    }

    @GetMapping("account/officer")
    public List<AccountOfficer> allAccountOfficer(Pageable pageable){
        return accountService.allAccountOfficer(pageable).toList();
    }

    @PostMapping("account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto){
        return accountService.createAccount(accountDto);
    }

    @GetMapping("account")
    public ResponseEntity<?> fetchCustomerAccountByCif(@RequestParam(name = "cif") String cif){
        return accountService.fetchAccountByCif(cif);
    }

    @GetMapping("account/validate")
    public ResponseEntity<?> validateAccount(@RequestParam(name = "accountNo") String accountNo){
        return accountService.validateAccount(accountNo);
    }
}
