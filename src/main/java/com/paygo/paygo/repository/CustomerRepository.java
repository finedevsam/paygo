package com.paygo.paygo.repository;

import com.paygo.paygo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByMnemonic(String mnemonic);
    boolean existsByEmail(String email);
    boolean existsByIdNo(String idNo);
    boolean existsByMobileNo(String mobileNo);
    boolean existsByCif(String cif);

    Customer findCustomerByCif(String cif);

}
