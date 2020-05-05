package com.company.bankingaccount.controller;


import com.company.bankingaccount.entity.Customer;
import com.company.bankingaccount.exception.AlreadyRegisteredCustomerException;
import com.company.bankingaccount.exception.CustomerNotFoundException;
import com.company.bankingaccount.service.ICustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method= RequestMethod.GET, value = "/customers")
    @ApiOperation(value="Get all customers", notes="Get all customers in the system", nickname="getAllCustomers")
    public ResponseEntity<List<Customer>> findAll(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET,value="/customers/{customerId}")
    @ApiOperation(value="Get customer by customerId", notes="Get the customerId", nickname="getByCustomerId")
    public ResponseEntity<Customer> findByCustomerId(@PathVariable String customerId){
        try {
            Customer customer = customerService.findByCustomerId(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);

        }catch(CustomerNotFoundException accountNotFoundEx){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method=RequestMethod.POST, value="/customers")
    @ApiOperation(value="Create a new customer", notes="Create a new customer", nickname="saveCustomer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer cust = new Customer();
        try {
            cust = customerService.saveCustomer(customer);
            return new ResponseEntity<>(cust, HttpStatus.OK);

        } catch (AlreadyRegisteredCustomerException e) {
            return new ResponseEntity<> (cust, HttpStatus.ALREADY_REPORTED);
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/customers/{customerId}")
    @ApiOperation(value="Delete customer", notes="Delete customer by id", nickname="deleteCustomer")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(CustomerNotFoundException ex){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/customers/{customerId}")
    @ApiOperation(value="Update customer", notes="Update customer by id", nickname="UpdateCustomer")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        Customer custom = new Customer();
        try {
            custom = customerService.updateCustomer(customerId,customer);
            return new ResponseEntity<>(custom, HttpStatus.OK);

        } catch(CustomerNotFoundException customerNotFoundEx){
            return new ResponseEntity<> (customer,HttpStatus.NOT_FOUND);
        }
    }





}
