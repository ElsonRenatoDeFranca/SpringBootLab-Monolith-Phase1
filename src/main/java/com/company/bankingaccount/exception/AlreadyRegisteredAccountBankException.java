package com.company.bankingaccount.exception;

public class AlreadyRegisteredAccountBankException extends Exception {

    private static final long serialVersionUID = 7997828512143245128L;

    public AlreadyRegisteredAccountBankException(String message) {
        super(message);
    }
}
