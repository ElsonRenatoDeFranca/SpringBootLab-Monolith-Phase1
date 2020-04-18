package com.company.bankingaccount.exception;

public class CustomerNotFoundException extends Exception {

    private static final long serialVersionUID = 457828512143245128L;

    public CustomerNotFoundException(String message) {
        super(message);
    }

}
