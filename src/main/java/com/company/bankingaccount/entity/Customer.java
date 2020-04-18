package com.company.bankingaccount.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name="CUSTOMER")
public class Customer {
        @Id
        @Column(name="ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        private Long id;

        @Column(name ="CUSTOMER_ID", length = 15)
        private String customerId;

        @Column(name ="FIRST_NAME", length = 30)
        private String firstName;

        @Column(name ="LAST_NAME", length = 30)
        private String lastName;

        @Column(name ="EMAIL", length = 40)
        private String email;

        //@ManyToMany(cascade=CascadeType.ALL)
        //private Set<BankAccount> bankAccount;

        /*@ManyToMany(cascade=CascadeType.ALL)
        @JoinTable(name="customer_account",
                joinColumns = @JoinColumn(name="account_number"),
                inverseJoinColumns = @JoinColumn(name="customer_id"))
        private Set<BankAccount> bankAccounts;


         */
}
