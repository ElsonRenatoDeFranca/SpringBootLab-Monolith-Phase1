package com.company.bankingaccount.controller;


import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.entity.TransferFund;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.BankAccountNotFoundException;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.ICoreBankService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreBankController {

    private final ICoreBankService coreBankService;

    @Autowired
    public CoreBankController(ICoreBankService coreBankService) {
        this.coreBankService = coreBankService;
    }

    @RequestMapping(method=RequestMethod.GET,value="/customers/{customerId}/accounts")
    @ApiOperation(value="Get customer by customerId", notes="Get the customerId", nickname="getByCustomerId")
    public ResponseEntity<Customer> findAccountsByCustomerId(@PathVariable String customerId){
        try {
            Customer customer = coreBankService.findAccountsByCustomerId(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);

        }catch(CustomerNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(method= RequestMethod.POST, value="/customers/{customerId}/accounts")
    @ApiOperation(value="Add an account to a customer", notes="Add an account to a customer", nickname="AddAccountToCustomer")
    public ResponseEntity<Customer> addAccountToCustomer(@PathVariable(name="customerId") String customerId, @RequestBody BankAccount bankAccount ) {
        Customer customer = new Customer();

        try {
            customer = coreBankService.addAccountToCustomer(customerId, bankAccount);
            return new ResponseEntity<>(customer, HttpStatus.OK);

        } catch (CustomerNotFoundException | BankAccountNotFoundException e) {
            return new ResponseEntity<> (customer, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method=RequestMethod.PUT, value="/accounts/transferfunds/")
    @ApiOperation(value="Transfer funds", notes="Transfer funds from customer from one account into another", nickname="transferFunds")
    public ResponseEntity<BankAccount> transferFunds(@RequestBody TransferFund transferFund) {
        BankAccount account = new BankAccount();
        try {
            account = coreBankService.transferFunds(transferFund);
            return new ResponseEntity<>(account, HttpStatus.OK);

        } catch(BankAccountNotFoundException ex){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }


}
