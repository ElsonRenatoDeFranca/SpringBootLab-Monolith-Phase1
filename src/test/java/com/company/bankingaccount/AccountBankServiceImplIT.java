package com.company.bankingaccount;


import com.company.bankingaccount.app.BankAccountApplication;
import com.company.bankingaccount.dao.IBankAccountRepository;
import com.company.bankingaccount.dao.ICustomerRepository;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.util.AccountTypeEnum;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@SpringBootTest(classes = BankAccountApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@RunWith(SpringRunner.class)
//@DataJpaTest
public class AccountBankServiceImplTest {

    private ICustomerRepository customerRepository;
    private IBankAccountRepository bankAccountRepository;

    @Autowired
    public AccountBankServiceImplTest(ICustomerRepository customerRepository, IBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }

    @Test
    @Order(1)
    @Transactional
    public void test_saveAccount(){
        BankAccount account = new BankAccount();
        account.setBalance(600);
        account.setAccountType(AccountTypeEnum.CURRENT_INDIVIDUAL.name());
        account.setAccountNumber("1001");

        bankAccountRepository.save(account);
    }

    @Test
    @Order(2)
    @Transactional
    public void test_addAccountToCustomer() {

        BankAccount account = this.bankAccountRepository.findByAccountNumber("1001");

        Customer customer = new Customer();
        customer.setCustomerId("100");
        customer.setFirstName("Abraham");
        customer.setLastName("Lincoln");
        customer.setEmail("abraham.lincoln@gmail.com");
        customer.getAccounts().add(account);

        customerRepository.save(customer);
    }

    @Test
    @Order(3)
    @Transactional
    public void test_findByCustomId() {
        Customer customer = customerRepository.findBycustomerId("1");
        System.out.println(customer);

        assertThat(customer, is(notNullValue()));
        assertThat(customer, hasProperty("accounts", hasSize(greaterThan(0))));
    }

    @Test
    @Order(4)
    @Transactional
    public void test_findAll(){
        List<Customer> customers = customerRepository.findAll();

        customers.stream().forEach(System.out::println);

        assertThat(customers, is(notNullValue()));
        assertThat(customers, hasSize(greaterThan(0)));
    }

}
