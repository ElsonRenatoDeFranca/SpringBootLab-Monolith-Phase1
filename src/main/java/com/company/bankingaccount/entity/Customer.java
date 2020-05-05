package com.company.bankingaccount.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name="CUSTOMER")
public class Customer implements Serializable {
        @Id
        @Column(name="ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        private Long id;

        @NaturalId
        @Column(name ="CUSTOMER_ID", length = 15)
        private String customerId;

        @Column(name ="FIRST_NAME", length = 30)
        private String firstName;

        @Column(name ="LAST_NAME", length = 30)
        private String lastName;

        @Column(name ="EMAIL", length = 40)
        private String email;

        @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
        @JoinTable(name="customer_account",
                joinColumns=@JoinColumn(name="CUSTOMER_ID", referencedColumnName="CUSTOMER_ID"),
                inverseJoinColumns = @JoinColumn(name="ACCOUNT_NUMBER",referencedColumnName="ACCOUNT_NUMBER"))
        private Set<BankAccount> accounts = new HashSet<>();


}
