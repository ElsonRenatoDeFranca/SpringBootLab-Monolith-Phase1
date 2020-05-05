package com.company.bankingaccount.service;


import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService {

    List<Customer> findAll();

    Customer findByCustomerId(String customerId) throws CustomerNotFoundException;

    Customer saveCustomer(Customer customer) throws AlreadyRegisteredCustomerException;

    void deleteCustomer(String customerId) throws CustomerNotFoundException;

    Customer updateCustomer(String customerId, Customer customer)  throws CustomerNotFoundException;

}
