package com.company.bankingaccount;

import com.company.bankingaccount.app.BankAccountApplication;
import com.company.bankingaccount.dao.ICustomerRepository;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.ICustomerService;
import com.company.bankingaccount.util.AccountTypeEnum;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= BankAccountApplication.class)
class AccountbankApplicationTests {

    AccountbankApplicationTests(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    void contextLoads() {
    }

    /*private final ICustomerRepository customerRepository;

    @Autowired
    AccountbankApplicationTests(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    @Transactional
    public void getCustomer(){
        Optional<Customer> optionalCustomer = customerRepository.findBycustomerId("1");
        Customer customer = optionalCustomer.get();
        assertThat(customer, hasProperty("bankAccounts",is(notNullValue())));
        assertThat(customer, hasProperty("bankAccounts",hasSize(greaterThan(0))));

    }

    @Test
    @Transactional
    public void saveCustomer(){

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("104");
        bankAccount.setBalance(340);
        bankAccount.setAccountType(AccountTypeEnum.CURRENT_INDIVIDUAL.name());

        Customer customer = new Customer();
        customer.setCustomerId("10");
        customer.setEmail("james.madison@gmail.com");
        customer.setFirstName("james");
        customer.setLastName("madison");
        customer.getAccounts().add(bankAccount);

        customerRepository.save(customer);
    }
*/


    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private final ICustomerService customerService;


    @Test
    public void testFindById() throws CustomerNotFoundException {
        //When
        when(customerRepository.findBycustomerId(anyString())).thenReturn(mockCustomer());

        Customer customer = customerService.findByCustomerId("1");

        //Then
        assertThat(customer, hasProperty("customerId", CoreMatchers.is(equalTo("1"))));
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
