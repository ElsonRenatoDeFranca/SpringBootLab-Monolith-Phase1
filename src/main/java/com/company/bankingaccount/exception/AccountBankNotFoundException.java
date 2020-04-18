package com.company.bankingaccount.exception;

public class AccountBankNotFoundException extends Exception {

    private static final long serialVersionUID = 987828512143245128L;

    public AccountBankNotFoundException(String message) {
        super(message);
    }
}
