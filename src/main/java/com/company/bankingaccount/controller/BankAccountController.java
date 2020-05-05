package com.company.bankingaccount.controller;

import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.exception.BankAccountNotFoundException;
import com.company.bankingaccount.exception.AlreadyRegisteredAccountBankException;
import com.company.bankingaccount.service.IBankAccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountController {

    private final IBankAccountService bankAccountService;

    @Autowired
    public BankAccountController(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @RequestMapping(method= RequestMethod.GET, value = "/accounts")
    @ApiOperation(value="Get all accounts", notes="Get all accounts in the system", nickname="getAllAccounts")
    public ResponseEntity<List<BankAccount>> findAll(){
        return new ResponseEntity<>(bankAccountService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET,value="/accounts/{bankAccountNumber}")
    @ApiOperation(value="Get account by accountNumber", notes="Get the accountNumber", nickname="getByAccountNumber")
    public ResponseEntity<BankAccount> findByAccountNumber(@PathVariable String bankAccountNumber){
        BankAccount account = new BankAccount();
        try {
            account = bankAccountService.findByAccountNumber(bankAccountNumber);
            return new ResponseEntity<>(account, HttpStatus.OK);

        }catch(BankAccountNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method=RequestMethod.POST, value="/accounts")
    @ApiOperation(value="Create a new account", notes="Create a new account", nickname="saveAccount")
    public ResponseEntity<BankAccount> saveBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount account = new BankAccount();
        try {
            account = bankAccountService.saveBankAccount(bankAccount);
            return new ResponseEntity<>(account, HttpStatus.OK);

        } catch (AlreadyRegisteredAccountBankException e) {
            return new ResponseEntity<> (account, HttpStatus.ALREADY_REPORTED);
        }
    }


    @RequestMapping(method=RequestMethod.DELETE, value="/accounts/{bankAccountNumber}")
    @ApiOperation(value="Delete account", notes="Delete an existing account", nickname="deleteAccount")
    public ResponseEntity<BankAccount> deleteBankAccount(@PathVariable String bankAccountNumber) {
        try {
            bankAccountService.deleteBankAccount(bankAccountNumber);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(BankAccountNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method=RequestMethod.PUT, value="/accounts/{bankAccountNumber}")
    @ApiOperation(value="Update account", notes="Update an existing account", nickname="UpdateAccount")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable String bankAccountNumber, @RequestBody BankAccount accountBank) {
        try {
            BankAccount bankAccount  = bankAccountService.updateBankAccount(bankAccountNumber,accountBank);
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);

        } catch(BankAccountNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }


}
