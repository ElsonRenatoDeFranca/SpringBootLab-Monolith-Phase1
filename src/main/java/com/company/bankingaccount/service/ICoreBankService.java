package com.company.bankingaccount.service;

import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.entity.TransferFund;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.BankAccountNotFoundException;
import com.company.bankingaccount.exception.CustomerNotFoundException;

public interface ICoreBankService {

    Customer addAccountToCustomer(String customerId, BankAccount bankAccount ) throws CustomerNotFoundException, BankAccountNotFoundException;
    BankAccount transferFunds(TransferFund transferFund) throws BankAccountNotFoundException;
    Customer findAccountsByCustomerId(String customerId) throws CustomerNotFoundException;

}
