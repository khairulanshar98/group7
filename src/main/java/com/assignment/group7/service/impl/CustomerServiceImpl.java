package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Customer;
import com.assignment.group7.repository.CustomerRepository;
import com.assignment.group7.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findByName(String name) throws RecordNotFoundException {
        try {
            return this.customerRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public Optional<Customer> findByEmail(String email) throws RecordNotFoundException {
        try {
            return this.customerRepository.findByEmail(email);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) throws RecordNotFoundException {
        try {
            return this.customerRepository.findByPhoneNumber(phoneNumber);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public Customer create(Customer customer) throws RecordNotCreatedException {
        try {
            if (this.customerRepository.existsById(customer.getCustomerId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.customerRepository.save(customer);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Record not created.").build();
        }
    }

    @Override
    public List<Customer> getAll() throws RecordNotFoundException {
        try {
            return this.customerRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<Customer> customers) throws RecordNotCreatedException {
        try {
            this.customerRepository.saveAll(customers);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Records not created.").build();
        }
    }
}
