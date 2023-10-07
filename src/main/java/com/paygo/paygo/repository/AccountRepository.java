package com.paygo.paygo.repository;

import com.paygo.paygo.entity.Account;
import com.paygo.paygo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAccountByCustomerAndAccountType(Customer customer, String accountType);
    boolean existsByMnemonic(String mnemonic);
    List<Account> findAllByCustomer(Customer customer);
}
