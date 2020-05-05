package com.company.bankingaccount.util;

public enum AccountTypeEnum {
    CURRENT_INDIVIDUAL("Current individual"),
    CURRENT_JOINT("Current Joint"),
    SAVINGS_INDIVIDUAL("Savings Individual"),
    SAVINGS_JOINT("Savings Joint");

    private final String accountType;

    AccountTypeEnum(String accountType) {
        this.accountType = accountType;
    }
    private String accountType() { return accountType; }
}
