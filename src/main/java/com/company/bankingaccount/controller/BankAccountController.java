package com.company.bankingaccount.controller;

import com.company.bankingaccount.entity.BankAccount;
import com.company.bankingaccount.exception.AccountBankNotFoundException;
import com.company.bankingaccount.exception.AlreadyRegisteredAccountBankException;
import com.company.bankingaccount.service.IBankAccountService;
import com.company.bankingaccount.vo.BankAccountVO;
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
    public ResponseEntity<List<BankAccountVO>> findAll(){
        return new ResponseEntity<>(bankAccountService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET,value="/accounts/{bankAccountNumber}")
    @ApiOperation(value="Get account by accountNumber", notes="Get the accountNumber", nickname="getByAccountNumber")
    public ResponseEntity<BankAccountVO> findByAccountNumber(@PathVariable String bankAccountNumber){
        try {
            BankAccountVO accountBankingVO = bankAccountService.findByAccountNumber(bankAccountNumber);
            return new ResponseEntity<>(accountBankingVO, HttpStatus.OK);

        }catch(AccountBankNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method=RequestMethod.POST, value="/accounts")
    @ApiOperation(value="Create a new account", notes="Create a new account", nickname="saveAccount")
    public ResponseEntity<BankAccountVO> saveAccountBanking(@RequestBody BankAccount bankAccount) {
        BankAccountVO accountBankingVO = new BankAccountVO();
        try {
            accountBankingVO = bankAccountService.saveAccountBank(bankAccount);
            return new ResponseEntity<>(accountBankingVO, HttpStatus.OK);

        } catch (AlreadyRegisteredAccountBankException e) {
            return new ResponseEntity<> (accountBankingVO, HttpStatus.ALREADY_REPORTED);
        }
    }


    @RequestMapping(method=RequestMethod.DELETE, value="/accounts/{bankAccountNumber}")
    @ApiOperation(value="Delete account", notes="Delete an existing account", nickname="deleteAccount")
    public ResponseEntity<BankAccountVO> deleteAccountBanking(@PathVariable String bankAccountNumber) {
        BankAccountVO accountBankingVO = new BankAccountVO();
        try {
            bankAccountService.deleteAccountBank(bankAccountNumber);
            return new ResponseEntity<>(accountBankingVO, HttpStatus.OK);

        } catch(AccountBankNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method=RequestMethod.PUT, value="/accounts/{bankAccountNumber}")
    @ApiOperation(value="Update account", notes="Update an existing account", nickname="UpdateAccount")
    public ResponseEntity<BankAccountVO> updateAccountBanking(@PathVariable String bankAccountNumber, @RequestBody BankAccount accountBank) {
        BankAccountVO accountBankingVO = new BankAccountVO();
        try {
            bankAccountService.updateAccountBank(bankAccountNumber,accountBank);
            return new ResponseEntity<>(accountBankingVO, HttpStatus.OK);

        } catch(AccountBankNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }


}
