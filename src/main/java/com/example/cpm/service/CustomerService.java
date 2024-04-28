package com.example.cpm.service;

import com.example.cpm.exception.ResourceNotFoundException;
import com.example.cpm.model.Customer;
import com.example.cpm.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Log
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        log.info("Getting all customers data from database");
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        log.info("Saving customer data to database");
        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(Long id){
        log.info(String.format("Getting customer data with id: %o", id));
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new ResourceNotFoundException(String.format("Customer with id %o is not found", id));
        return customer;
    }

    public void deleteCustomer(Long id) {
        log.info(String.format("Deleting customer data with id: %o", id));
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new ResourceNotFoundException(String.format("Customer with id %o is not found", id));
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer){
        log.info(String.format("Updating customer data with id: %o", customer.getId()));
        return customerRepository.save(customer);
    }

}
