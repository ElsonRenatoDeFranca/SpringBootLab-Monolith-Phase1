package com.company.bankingaccount.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name="BANK_ACCOUNT")
public class BankAccount {

    @Id
    @Column(name="BANK_ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long bankAccountId;

    @Column(name ="ACCOUNT_NUMBER", length = 25)
    private String accountNumber;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ACCOUNT_TYPE_ID")
    private AccountType accountType;

    @Column(name="BALANCE", length = 10)
    private double balance;
}
