package com.paygo.paygo.repository;

import com.paygo.paygo.entity.AccountOfficer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOfficerRepository extends JpaRepository<AccountOfficer, String> {

    boolean existsByCode(String code);
    boolean existsByEmail(String email);
}
