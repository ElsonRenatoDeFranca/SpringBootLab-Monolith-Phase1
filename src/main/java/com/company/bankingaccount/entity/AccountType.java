package com.company.bankingaccount.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name="ACCOUNT_TYPE")
public class AccountType {


    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name ="ACCOUNT_TYPE_ID", length = 5)
    private Long accountTypeId;

    @Column(name ="ACCOUNT_TYPE_DESC", length = 25)
    private String accountTypeDesc;

    @OneToOne(mappedBy="accountType", cascade = CascadeType.ALL)
    private BankAccount bankAccount;


}
