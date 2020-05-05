package com.company.bankingaccount.service;


import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.exception.BankAccountNotFoundException;
import com.company.bankingaccount.exception.AlreadyRegisteredAccountBankException;

import java.util.List;

public interface IBankAccountService {

    List<BankAccount> findAll();

    BankAccount findByAccountNumber(String bankAccountNumber) throws BankAccountNotFoundException;

    BankAccount saveBankAccount(BankAccount bankAccount) throws AlreadyRegisteredAccountBankException;

    void deleteBankAccount(String bankAccountNumber) throws BankAccountNotFoundException;

    BankAccount updateBankAccount(String bankAccountNumber, BankAccount bankAccount)  throws BankAccountNotFoundException;
}
