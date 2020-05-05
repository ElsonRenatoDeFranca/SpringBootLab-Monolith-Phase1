package com.company.bankingaccount;


import com.company.bankingaccount.app.BankAccountApplication;
import com.company.bankingaccount.dao.IBankAccountRepository;
import com.company.bankingaccount.dao.ICustomerRepository;
import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.service.IBankAccountService;
import com.company.bankingaccount.util.AccountTypeEnum;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BankAccountApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

    @Mock
    private IBankAccountRepository bankAccountRepository;
    @InjectMocks
    private IBankAccountService bankAccountService;

    /*@Autowired
    public BankAccountServiceImplTest(ICustomerRepository customerRepository, IBankAccountRepository bankAccountRepository) {
   //     this.bankAccountRepository = bankAccountRepository;
      //  this.customerRepository = customerRepository;
    }*/


    @Test
    public void test_findAll(){
        List<BankAccount>  accountList = mockAccounts();

        when(bankAccountRepository.findAll()).thenReturn(accountList);

        List accounts = bankAccountRepository.findAll();

        assertThat(accounts, is(notNullValue()));
        //assertThat(accounts, hasSize(greaterThan(0)));
    }

    private List<BankAccount> mockAccounts(){
        List<BankAccount> accounts = new ArrayList<>();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(200);
        bankAccount.setAccountType(AccountTypeEnum.CURRENT_INDIVIDUAL.name());
        bankAccount.setAccountNumber("1");
        accounts.add(bankAccount);
        return accounts;

    }

}
