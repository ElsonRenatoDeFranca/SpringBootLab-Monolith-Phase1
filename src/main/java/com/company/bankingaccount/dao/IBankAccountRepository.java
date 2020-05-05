package com.company.bankingaccount.dao;


import com.company.bankingaccount.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IBankAccountRepository extends JpaRepository<BankAccount, String> {
    BankAccount findByAccountNumber(String bankAccountNumber);
    void deleteByAccountNumber(String bankAccountNumber);
}
