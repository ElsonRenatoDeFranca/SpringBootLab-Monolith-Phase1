package com.company.bankingaccount.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TransferFund implements Serializable {
    private String fromAccount;
    private String toAccount;
    private double amount;
}
