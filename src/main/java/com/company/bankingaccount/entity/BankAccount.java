package com.company.bankingaccount.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name="BANK_ACCOUNT")
public class BankAccount implements Serializable {

    @Id
    @Column(name="BANK_ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long bankAccountId;

    @NaturalId
    @Column(name ="ACCOUNT_NUMBER", length = 25)
    private String accountNumber;

    @Column(name ="ACCOUNT_TYPE", length = 30)
    private String accountType;

    @Column(name="BALANCE", length = 10)
    private double balance;


}
