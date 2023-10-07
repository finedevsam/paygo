package com.paygo.paygo.service;

import com.paygo.paygo.dto.AccountDto;
import com.paygo.paygo.dto.AccountOfficerDto;
import com.paygo.paygo.entity.AccountOfficer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<?> createAccount(AccountDto accountDto);

    ResponseEntity<?> createAccountOfficer(AccountOfficerDto accountOfficerDto);

    Page<AccountOfficer> allAccountOfficer(Pageable pageable);

    ResponseEntity<?> fetchAccountByCif(String cif);
}
