package com.paygo.paygo.service;

import com.paygo.paygo.dto.AccountDto;
import com.paygo.paygo.dto.AccountOfficerDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<?> createAccount(AccountDto accountDto);

    ResponseEntity<?> createAccountOfficer(AccountOfficerDto accountOfficerDto);
}
