package com.company.bankingaccount.service.impl;

import com.company.bankingaccount.converter.BankAccountConverter;
import com.company.bankingaccount.dao.IBankAccountRepository;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.exception.AccountBankNotFoundException;
import com.company.bankingaccount.exception.AlreadyRegisteredAccountBankException;
import com.company.bankingaccount.service.IBankAccountService;
import com.company.bankingaccount.vo.BankAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements IBankAccountService {

    private final IBankAccountRepository accountBankingRepository;
    private final BankAccountConverter accountBankingConverter;

    private static final String ACCOUNT_NOT_FOUND = "Account Banking Not found exception";
    private static final String ACCOUNT_ALREADY_EXISTS = "Account Banking already exists";

    @Autowired
    public BankAccountServiceImpl(IBankAccountRepository accountBankingRepository, BankAccountConverter accountBankingConverter) {
        this.accountBankingRepository = accountBankingRepository;
        this.accountBankingConverter = accountBankingConverter;
    }

    @Override
    public List<BankAccountVO> findAll() {
        return accountBankingRepository.findAll().stream().map(accountBankingConverter ::convertEntityToVO).
               collect(Collectors.toList());

    }

    @Override
    public BankAccountVO findByAccountNumber(String bankAccountNumber) throws AccountBankNotFoundException {
        return accountBankingRepository.findByAccountNumber(bankAccountNumber).map(
                accountBankingConverter::convertEntityToVO).orElseThrow(() ->
                new AccountBankNotFoundException(ACCOUNT_NOT_FOUND));
    }

    @Override
    public BankAccountVO saveAccountBank(BankAccount providedAccountBank) throws AlreadyRegisteredAccountBankException {

        if(!accountBankAlreadyExists(providedAccountBank.getAccountNumber())){
            return accountBankingConverter.convertEntityToVO(accountBankingRepository.saveAndFlush(providedAccountBank));
        }else{
            throw new AlreadyRegisteredAccountBankException(ACCOUNT_ALREADY_EXISTS);
        }

    }

    @Override
    public void deleteAccountBank(String bankAccountNumber) throws AccountBankNotFoundException {

        if(accountBankAlreadyExists(bankAccountNumber)){
            accountBankingRepository.deleteByAccountNumber(bankAccountNumber);
        }else{
            throw new AccountBankNotFoundException(ACCOUNT_NOT_FOUND);
        }

    }

    @Override
    public BankAccountVO updateAccountBank(String bankAccountNumber, BankAccount accountBanking) throws AccountBankNotFoundException {
        if(accountBankAlreadyExists(bankAccountNumber)) {
            return accountBankingConverter.convertEntityToVO(accountBankingRepository.saveAndFlush(accountBanking));
        }else{
            throw new AccountBankNotFoundException(ACCOUNT_NOT_FOUND);
        }

    }

    private boolean accountBankAlreadyExists(String bankAccountNumber){
        BankAccountVO alreadyRegisteredAccountBanking = accountBankingRepository.
                findByAccountNumber(bankAccountNumber).map(accountBankingConverter::convertEntityToVO).get();

        return alreadyRegisteredAccountBanking.getAccountNumber().equals(bankAccountNumber);
    }


}
