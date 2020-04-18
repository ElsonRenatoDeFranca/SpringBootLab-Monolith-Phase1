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
public class CustomerVO implements Serializable {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
}
