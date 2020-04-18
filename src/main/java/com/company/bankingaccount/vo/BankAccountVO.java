package com.company.bankingaccount.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BankAccountVO implements Serializable {
    private String accountNumber;
    private AccountTypeVO accountTypeVO;
    private double balance;
}
