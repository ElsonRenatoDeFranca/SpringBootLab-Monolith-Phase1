package com.company.bankingaccount.service.impl;

import com.company.bankingaccount.converter.CustomerConverter;
import com.company.bankingaccount.dao.ICustomerRepository;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.ICustomerService;
import com.company.bankingaccount.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    private static final String CUSTOMER_NOT_FOUND = "Customer Not found exception";
    private static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public List<CustomerVO> findAll() {
        return customerRepository.findAll().stream().
                map(customerConverter :: convertEntityToVO).collect(Collectors.toList());
    }

    @Override
    public CustomerVO findByCustomerId(String customerId) throws CustomerNotFoundException {
        return customerRepository.findBycustomerId(customerId).map(
                customerConverter::convertEntityToVO).orElseThrow(() ->
                new CustomerNotFoundException(CUSTOMER_NOT_FOUND));
    }

    @Override
    public CustomerVO saveCustomer(Customer customer) throws AlreadyRegisteredCustomerException {
        if(!customerAlreadyExists(customer.getCustomerId())){
            return customerConverter.convertEntityToVO(customerRepository.saveAndFlush(customer));
        }else{
            throw new AlreadyRegisteredCustomerException(CUSTOMER_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public void deleteCustomer(String customerId) throws CustomerNotFoundException {
        if(customerAlreadyExists(customerId)){
            customerRepository.deleteBycustomerId(customerId);
        }else{
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public CustomerVO updateCustomer(String customerId, Customer customer) throws CustomerNotFoundException {

        if(customerAlreadyExists(customerId)) {

            return customerConverter.convertEntityToVO(customerRepository.save(customer));
        }else{
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }


    private boolean customerAlreadyExists(String customerId){
        Optional<CustomerVO> customerVO = customerRepository.
                findBycustomerId(customerId).map(customerConverter::convertEntityToVO);

        return customerVO.isPresent() && customerVO.get().getCustomerId().equals(customerId);

    }
}
