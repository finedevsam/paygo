package com.paygo.paygo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paygo.paygo.entity.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, String>{

    boolean existsByCode(String code);
    AccountType findAccountTypeByCode(String code);
    
}
