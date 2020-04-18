package com.company.bankingaccount.service;


import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.vo.CustomerVO;

import java.util.List;

public interface ICustomerService {

    List<CustomerVO> findAll();

    CustomerVO findByCustomerId(String customerId) throws CustomerNotFoundException;

    CustomerVO saveCustomer(Customer customer) throws AlreadyRegisteredCustomerException;

    void deleteCustomer(String customerId) throws CustomerNotFoundException;

    CustomerVO updateCustomer(String customerId, Customer customer)  throws CustomerNotFoundException;

}
