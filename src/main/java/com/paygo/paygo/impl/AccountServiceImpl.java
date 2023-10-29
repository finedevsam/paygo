package com.paygo.paygo.impl;

import com.paygo.paygo.dto.AccountDto;
import com.paygo.paygo.dto.AccountOfficerDto;
import com.paygo.paygo.dto.FundTransferDto;
import com.paygo.paygo.entity.Account;
import com.paygo.paygo.entity.AccountOfficer;
import com.paygo.paygo.entity.Customer;
import com.paygo.paygo.repository.AccountOfficerRepository;
import com.paygo.paygo.repository.AccountRepository;
import com.paygo.paygo.repository.AccountTypeRepository;
import com.paygo.paygo.repository.CustomerRepository;
import com.paygo.paygo.service.AccountService;
import com.paygo.paygo.utils.AccountManager;
import com.paygo.paygo.utils.Core;
import com.paygo.paygo.utils.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DataResponse dataResponse;

    @Autowired
    private AccountOfficerRepository accountOfficerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Core core;


    @Override
    public ResponseEntity<?> createAccount(AccountDto accountDto) {
        if (!accountOfficerRepository.existsByCode(accountDto.getAccountOfficer())){
            return dataResponse.dataResponse("99", "fail", errorMsg("Invalid account officer"), HttpStatus.BAD_REQUEST);
        }

        if(!accountTypeRepository.existsByCode(accountDto.getAccountType())){
            return dataResponse.dataResponse("99", "fail", errorMsg("Invalid account type"), HttpStatus.BAD_REQUEST);
        }

        if(!customerRepository.existsByCif(accountDto.getCustomerCif())){
            return dataResponse.dataResponse("99", "fail", errorMsg("Customer CIF not recognized"), HttpStatus.BAD_REQUEST);
        }

        if(accountRepository.existsByMnemonic(accountDto.getMnemonic())){
            return dataResponse.dataResponse("99", "fail", errorMsg("Mnemonic has already been used"), HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerRepository.findCustomerByCif(accountDto.getCustomerCif());
        if(!Objects.equals(accountRepository.findAccountByCustomerAndAccountType(customer, accountDto.getAccountType()), null)){
            return dataResponse.dataResponse("99", "fail", errorMsg("Customer Already have this type of account"), HttpStatus.BAD_REQUEST);
        }

        Account account = accountManager.createAccount(accountDto);
        return dataResponse.dataResponse("00", "success", account, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createAccountOfficer(AccountOfficerDto accountOfficerDto) {
        if(accountOfficerRepository.existsByEmail(accountOfficerDto.getEmail())){
            return dataResponse.dataResponse("99", "fail", errorMsg("Account officer email already exist"), HttpStatus.BAD_REQUEST);
        }
        try {
            AccountOfficer accountOfficer = new AccountOfficer();
            accountOfficer.setCode(core.getCode());
            accountOfficer.setEmail(accountOfficerDto.getEmail());
            accountOfficer.setName(accountOfficerDto.getName());
            accountOfficer.setMobileNumber(accountOfficerDto.getMobileNumber());
            accountOfficerRepository.save(accountOfficer);
            return dataResponse.dataResponse("00", "success", accountOfficer, HttpStatus.OK);
        }catch (Exception e){
            return dataResponse.dataResponse("99", "fail", errorMsg(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<AccountOfficer> allAccountOfficer(Pageable pageable) {
        return accountOfficerRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> fetchAccountByCif(String cif) {
        if(!customerRepository.existsByCif(cif)){
            return dataResponse.dataResponse("99", "fail", errorMsg("Invalid CIF"), HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerRepository.findCustomerByCif(cif);
        List<Account> accounts = accountRepository.findAllByCustomer(customer);
        return dataResponse.dataResponse("00", "success", accounts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> validateAccount(String accountNo) {
        Account account = accountRepository.findAccountsByAccountNumber(accountNo);
        if (Objects.isNull(account)){
            return dataResponse.dataResponse("99", "fail", errorMsg("Account not Valid"), HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerRepository.findCustomerById(account.getCustomer().getId());
        Map<String, Object> data = new HashMap<>();
        data.put("name", String.format("%s %s", customer.getFirstName(), customer.getLastName()));
        return dataResponse.dataResponse("OO", "success", data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fundTransfer(FundTransferDto fundTransferDto) {
        Account debit = accountRepository.findAccountsByAccountNumber(fundTransferDto.getDebitAccount());
        Account credit = accountRepository.findAccountsByAccountNumber(fundTransferDto.getCreditAccount());

        if(Objects.isNull(debit)){
            return dataResponse.dataResponse("99", "fail", errorMsg("Debit account not valid"), HttpStatus.BAD_REQUEST);
        }

        if(Objects.isNull(credit)){
            return dataResponse.dataResponse("99", "fail", errorMsg("Credit account not valid"), HttpStatus.BAD_REQUEST);
        }

        double amount = Double.parseDouble(fundTransferDto.getAmount());
        double senderBal = Double.parseDouble(debit.getWorkingBalance());
        double receiverBal = Double.parseDouble(credit.getWorkingBalance());

        if(senderBal > amount){
            return dataResponse.dataResponse("01", "fail", errorMsg("Insufficient balance"), HttpStatus.BAD_REQUEST);
        }

        double newSenderBal = senderBal - amount;
        double newReceiverBal = receiverBal + amount;

        debit.setWorkingBalance(String.valueOf(newSenderBal));
        accountRepository.save(debit);

        credit.setWorkingBalance(String.valueOf(newReceiverBal));
        accountRepository.save(credit);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Transaction successful");
        return dataResponse.dataResponse("00", "success", data, HttpStatus.OK);
    }

    private Map<Object, Object> errorMsg(String message){
        Map<Object, Object> error = new HashMap<>();
        error.put("message", message);
        return error;
    }
}
