package com.company.bankingaccount.dao;

import com.company.bankingaccount.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface ICustomerRepository extends JpaRepository<Customer, String> {
    Customer findBycustomerId(String customerId);
    void deleteBycustomerId(String customerId);


}
