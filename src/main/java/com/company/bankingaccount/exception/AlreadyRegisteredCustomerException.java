package com.company.bankingaccount.exception;

public class AlreadyRegisteredCustomerException  extends Exception {

    private static final long serialVersionUID = 217828512143245128L;

    public AlreadyRegisteredCustomerException(String message) {
        super(message);
    }

}
