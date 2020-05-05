package com.company.bankingaccount.vo;

import com.company.bankingaccount.entity.BankAccount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CustomerVO implements Serializable {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<BankAccount> bankAccounts;
}
