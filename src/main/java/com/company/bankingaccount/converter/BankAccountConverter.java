package com.company.bankingaccount.converter;

import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.vo.BankAccountVO;
import org.springframework.stereotype.Component;

@Component
public class BankAccountConverter {

    public BankAccountVO convertEntityToVO(BankAccount bankAccount) {
        BankAccountVO bankAccountVO = new BankAccountVO();

        if(bankAccount != null) {
            bankAccountVO.setAccountNumber(bankAccount.getAccountNumber());
            bankAccountVO.setAccountType(bankAccount.getAccountType());
            bankAccountVO.setBalance(bankAccount.getBalance());
        }
        return bankAccountVO;
    }


    public BankAccount convertVOToEntity(BankAccountVO bankAccountVO){
        BankAccount bankAccount = new BankAccount();

        if(bankAccount != null){
            bankAccount.setAccountNumber(bankAccountVO.getAccountNumber());
            bankAccount.setAccountType(bankAccountVO.getAccountType());
            bankAccount.setBalance(bankAccountVO.getBalance());
        }

        return bankAccount;
    }


}
