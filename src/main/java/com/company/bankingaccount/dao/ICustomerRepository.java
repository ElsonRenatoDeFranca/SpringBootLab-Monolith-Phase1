package com.company.bankingaccount.dao;


import com.company.bankingaccount.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ICustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findBycustomerId(String customerId);
    void deleteBycustomerId(String customerId);
    //@Modifying
    //@Query("update Customer u set u.firstname = ?1, u.lastname = ?2, u.email = ?3  where u.customerId = ?4")
    //void setCustomerInfoByCustomerId(String firstname, String lastname, String email, Integer customerId);
}
