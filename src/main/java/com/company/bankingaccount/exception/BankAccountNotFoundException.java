package com.company.bankingaccount.exception;

public class BankAccountNotFoundException extends Exception {

    private static final long serialVersionUID = 987828512143245128L;

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
