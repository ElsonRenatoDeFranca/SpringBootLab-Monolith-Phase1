package com.company.bankingaccount.service.impl;

import com.company.bankingaccount.dao.IBankAccountRepository;
import com.company.bankingaccount.dao.ICustomerRepository;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final IBankAccountRepository bankAccountRepository;

    private static final String CUSTOMER_NOT_FOUND = "Customer Not found exception";
    private static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository,IBankAccountRepository bankAccountRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByCustomerId(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findBycustomerId(customerId);

        if (customer == null)
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);

        return customer;
    }

    @Override
    public Customer saveCustomer(Customer customer) throws AlreadyRegisteredCustomerException {
        if(!customerAlreadyExists(customer.getCustomerId())){
            return customerRepository.saveAndFlush(customer);
        }else{
            throw new AlreadyRegisteredCustomerException(CUSTOMER_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteCustomer(String customerId) throws CustomerNotFoundException {
        if(customerAlreadyExists(customerId)){
            customerRepository.deleteBycustomerId(customerId);
        }else{
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public Customer updateCustomer(String customerId, Customer newCustomer) throws CustomerNotFoundException {

        Customer existingCustomer = customerRepository.findBycustomerId(customerId);

        if(customerAlreadyExists(customerId)) {
            existingCustomer.setLastName(newCustomer.getLastName());
            existingCustomer.setFirstName(newCustomer.getFirstName());
            existingCustomer.setEmail(newCustomer.getEmail());

            return customerRepository.save(existingCustomer);
        }else{
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }

    private boolean customerAlreadyExists(String customerId){
        Customer customer = customerRepository.findBycustomerId(customerId);
        return customer != null;
    }
}
