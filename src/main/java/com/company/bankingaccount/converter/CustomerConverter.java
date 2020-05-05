package com.company.bankingaccount.converter;

import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.vo.CustomerVO;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public CustomerVO convertEntityToVO(Customer customer) {
        CustomerVO customerVO = new CustomerVO();
        if(customer != null){
            customerVO.setCustomerId(customer.getCustomerId());
            customerVO.setFirstName(customer.getFirstName());
            customerVO.setLastName(customer.getLastName());
            customerVO.setEmail(customer.getEmail());
        }
        return customerVO;
    }

    public Customer convertVoToEntity(CustomerVO customerVO) {
        Customer customer = new Customer();
        if(customerVO != null){
            customer.setCustomerId(customerVO.getCustomerId());
            customer.setFirstName(customerVO.getFirstName());
            customer.setLastName(customerVO.getLastName());
            customer.setEmail(customerVO.getEmail());
        }
        return customer;
    }
}
