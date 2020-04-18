package com.company.bankingaccount.util;

public enum AccountTypeEnum {
    CURRENT_INDIVIDUAL(1L),
    CURRENT_JOINT(2L),
    SAVINGS_INDIVIDUAL(3L),
    SAVINGS_JOINT(4L);

    private final Long accountType;

    AccountTypeEnum(Long accountType) {
        this.accountType = accountType;
    }
    private Long accountType() { return accountType; }
}
