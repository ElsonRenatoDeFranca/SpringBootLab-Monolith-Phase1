package com.company.bankingaccount.service.impl;

import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.entity.TransferFund;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.BankAccountNotFoundException;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.IBankAccountService;
import com.company.bankingaccount.service.ICoreBankService;
import com.company.bankingaccount.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CoreBankServiceImpl implements ICoreBankService {

    private final ICustomerService customerService;
    private final IBankAccountService bankAccountService;

    private static final String CUSTOMER_NOT_FOUND = "Customer not found";
    private static final String ACCOUNT_NOT_FOUND = "Bank account not found";

    @Autowired
    public CoreBankServiceImpl(ICustomerService customerService, IBankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService= bankAccountService;
    }

    @Override
    public Customer addAccountToCustomer(String customerId, BankAccount bankAccount) throws CustomerNotFoundException, BankAccountNotFoundException {

        Customer customer = customerService.findByCustomerId(customerId);
        BankAccount account = bankAccountService.findByAccountNumber(bankAccount.getAccountNumber());
        try {
            if (customerId.equals(customer.getCustomerId())) {
                customer.getAccounts().add(account);
                return customerService.saveCustomer(customer);
            } else {
                throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
            }
        } catch (AlreadyRegisteredCustomerException e) {
            return customer;
        }

    }

    @Override
    public BankAccount transferFunds(TransferFund transferFund) throws BankAccountNotFoundException {

        BankAccount existingFromAccount = bankAccountService.findByAccountNumber(transferFund.getFromAccount());
        BankAccount existingToAccount = bankAccountService.findByAccountNumber(transferFund.getToAccount());

        if(existingFromAccount != null && existingToAccount != null){
            double decrementedAmount = existingFromAccount.getBalance() - transferFund.getAmount();
            double incrementedAmount = existingToAccount.getBalance() + transferFund.getAmount();

            existingFromAccount.setBalance(decrementedAmount);
            existingToAccount.setBalance(incrementedAmount);
        }else{
            throw new BankAccountNotFoundException(ACCOUNT_NOT_FOUND);
        }

        return existingToAccount;
    }

    @Override
    public Customer findAccountsByCustomerId(String customerId) throws CustomerNotFoundException {

        //Customer customer = customerRepository.findBycustomerId(customerId);
        Customer customer = customerService.findByCustomerId(customerId);

        if (customer == null)
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);

        return customer;
    }



}
