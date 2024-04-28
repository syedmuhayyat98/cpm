package com.example.cpm.controller;

import com.example.cpm.exception.InvalidInputException;
import com.example.cpm.exception.ResourceNotFoundException;
import com.example.cpm.model.Customer;
import com.example.cpm.service.CustomerService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {

        if (StringUtils.isBlank(customer.getFirstName())){
            throw new InvalidInputException("Customer first name from Request Body is null!");
        }
        if (StringUtils.isBlank(customer.getLastName())){
            throw new InvalidInputException("Customer last name from Request Body is null!");
        }
        if (StringUtils.isBlank(customer.getEmailOffice())){
            throw new InvalidInputException("Customer office email from Request Body is null!");
        }
        if (StringUtils.isBlank(customer.getEmailPersonal())){
            throw new InvalidInputException("Customer personal email from Request Body is null!");
        }
        Customer createdCustomer = customerService.saveCustomer(customer);

        return createdCustomer;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public Customer updateCustomer(@RequestBody Customer customer) {

        Optional<Customer> optionalCustomer = customerService.findCustomerById(customer.getId());

        if (optionalCustomer.isPresent()) {
            Customer updatedCustomer = customerService.updateCustomer(customer);
            return updatedCustomer;
        } else {
            throw new ResourceNotFoundException(String.format("Customer with id %o not found", customer.getId()));
        }
    }
}
