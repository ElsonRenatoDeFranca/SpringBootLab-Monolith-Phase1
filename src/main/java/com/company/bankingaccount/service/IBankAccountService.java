package com.company.bankingaccount.service;


import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.exception.AccountBankNotFoundException;
import com.company.bankingaccount.exception.AlreadyRegisteredAccountBankException;
import com.company.bankingaccount.vo.BankAccountVO;

import java.util.List;

public interface IBankAccountService {

    List<BankAccountVO> findAll();

    BankAccountVO findByAccountNumber(String bankAccountNumber) throws AccountBankNotFoundException;

    BankAccountVO saveAccountBank(BankAccount bankAccount) throws AlreadyRegisteredAccountBankException;

    void deleteAccountBank(String bankAccountNumber) throws AccountBankNotFoundException;

    BankAccountVO updateAccountBank(String bankAccountNumber, BankAccount bankAccount)  throws AccountBankNotFoundException;
}
