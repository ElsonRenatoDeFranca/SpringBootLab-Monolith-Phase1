package com.company.bankingaccount.service.impl;

import com.company.bankingaccount.dao.IBankAccountRepository;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.exception.BankAccountNotFoundException;
import com.company.bankingaccount.exception.AlreadyRegisteredAccountBankException;
import com.company.bankingaccount.service.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService {

    private final IBankAccountRepository accountBankingRepository;

    private static final String ACCOUNT_NOT_FOUND = "Account Banking Not found exception";
    private static final String ACCOUNT_ALREADY_EXISTS = "Account Banking already exists";

    @Autowired
    public BankAccountServiceImpl(IBankAccountRepository accountBankingRepository) {
        this.accountBankingRepository = accountBankingRepository;
    }

    @Override
    public List<BankAccount> findAll() {
        return accountBankingRepository.findAll();

    }

    @Override
    public BankAccount findByAccountNumber(String bankAccountNumber) throws BankAccountNotFoundException {
        BankAccount bankAccount = accountBankingRepository.findByAccountNumber(bankAccountNumber);

        if(bankAccount == null)
            throw new BankAccountNotFoundException(ACCOUNT_NOT_FOUND);

        return bankAccount;
    }

    @Override
    public BankAccount saveBankAccount(BankAccount providedAccountBank) throws AlreadyRegisteredAccountBankException {

        if(!bankAccountAlreadyExists(providedAccountBank.getAccountNumber())){
            return accountBankingRepository.saveAndFlush(providedAccountBank);
        }else{
            throw new AlreadyRegisteredAccountBankException(ACCOUNT_ALREADY_EXISTS);
        }

    }

    @Override
    public void deleteBankAccount(String bankAccountNumber) throws BankAccountNotFoundException {

        if(bankAccountAlreadyExists(bankAccountNumber)){
            accountBankingRepository.deleteByAccountNumber(bankAccountNumber);
        }else{
            throw new BankAccountNotFoundException(ACCOUNT_NOT_FOUND);
        }

    }

    @Override
    public BankAccount updateBankAccount(String bankAccountNumber, BankAccount newAccount) throws BankAccountNotFoundException {

        BankAccount existingAccount = accountBankingRepository.findByAccountNumber(bankAccountNumber);

        if(bankAccountAlreadyExists(bankAccountNumber)) {
            existingAccount.setAccountType(newAccount.getAccountType());
            existingAccount.setBalance(newAccount.getBalance());

            return accountBankingRepository.saveAndFlush(existingAccount);
        }else{
            throw new BankAccountNotFoundException(ACCOUNT_NOT_FOUND);
        }

    }

    private boolean bankAccountAlreadyExists(String bankAccountNumber){
        BankAccount bankAccount = accountBankingRepository.findByAccountNumber(bankAccountNumber);
        return bankAccount != null;
    }


}
