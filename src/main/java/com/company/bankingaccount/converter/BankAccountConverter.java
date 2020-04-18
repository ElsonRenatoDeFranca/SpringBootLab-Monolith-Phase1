package com.company.bankingaccount.converter;

import com.company.bankingaccount.entity.AccountType;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.vo.AccountTypeVO;
import com.company.bankingaccount.vo.BankAccountVO;
import org.springframework.stereotype.Component;

@Component
public class BankAccountConverter {

    public BankAccountVO convertEntityToVO(BankAccount bankAccount) {
        BankAccountVO bankAccountVO = new BankAccountVO();

        if(bankAccount != null) {
            AccountTypeVO accountTypeVO = convertAccountTypeEntityToVO(bankAccount.getAccountType());
            bankAccountVO.setAccountNumber(bankAccount.getAccountNumber());
            bankAccountVO.setAccountTypeVO(accountTypeVO);
            bankAccountVO.setBalance(bankAccount.getBalance());
        }
        return bankAccountVO;
    }

    private AccountTypeVO convertAccountTypeEntityToVO(AccountType accountType){
        AccountTypeVO accountTypeVO = new AccountTypeVO();

        if(accountType != null) {
            accountTypeVO.setAccountTypeId(accountType.getAccountTypeId());
            accountTypeVO.setAccountTypeDesc(accountType.getAccountTypeDesc());
        }

        return accountTypeVO;
    }


}
