package com.company.bankingaccount;


import com.company.bankingaccount.app.BankAccountApplication;
import com.company.bankingaccount.dao.ICustomerRepository;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.ICustomerService;
import com.company.bankingaccount.util.AccountTypeEnum;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootTest(classes = BankAccountApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private final ICustomerService customerService;

    public CustomerServiceImplTest(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @Test
    public void testfindAll(){
        List<Customer>  customerList = new ArrayList<>();
        customerList.add(mockCustomer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List customers = customerService.findAll();
        assertThat(customers, is(CoreMatchers.notNullValue()));
    }

    @Test
    public void testFindById() throws CustomerNotFoundException {
        //When
        when(customerRepository.findBycustomerId(anyString())).thenReturn(mockCustomer());

        Customer customer = customerService.findByCustomerId("1");

        //Then
        assertThat(customer, hasProperty("customerId",is(equalTo("1"))));
    }

    private Customer mockCustomer(){
        Customer customer = new Customer();
        customer.setEmail("myemail@gmail.com");
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setCustomerId("1");
        customer.setAccounts(mockAccounts());

        return customer;
    }

    private Set<BankAccount> mockAccounts(){
        Set<BankAccount> accounts = new HashSet();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(200);
        bankAccount.setAccountType(AccountTypeEnum.CURRENT_INDIVIDUAL.name());
        bankAccount.setAccountNumber("1");
        accounts.add(bankAccount);

        return accounts;

    }

}
