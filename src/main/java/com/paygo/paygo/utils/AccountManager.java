package com.paygo.paygo.utils;

import com.paygo.paygo.dto.AccountDto;
import com.paygo.paygo.entity.Account;
import com.paygo.paygo.entity.Customer;
import com.paygo.paygo.repository.AccountRepository;
import com.paygo.paygo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Component
public class AccountManager {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${account_number_length}")
    private static String accountNumberLength;

    @Autowired
    private CustomerRepository customerRepository;

//    Generate account number
    private static String accountNumber() {
        System.out.println(accountNumberLength);
        Random random = new Random();
        int maxValue = (int) Math.pow(10, 7);
        int randomNumber = random.nextInt(maxValue);
        return String.valueOf(randomNumber);
    }

    @Transactional
    public Account createAccount(AccountDto accountDto){
        Customer customer = customerRepository.findCustomerByCif(accountDto.getCustomerCif());
        System.out.printf("I am out here:= %s%n", accountNumber());
        String accountNumber = String.format("%s%s", accountDto.getAccountType(), accountNumber());
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountDto.getAccountType());
        account.setAccountOfficer(accountDto.getAccountOfficer());
        account.setCustomer(customer);
        account.setMnemonic(accountDto.getMnemonic());
        return accountRepository.save(account);
    }
}
