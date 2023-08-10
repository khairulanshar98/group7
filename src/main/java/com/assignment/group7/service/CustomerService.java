package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByName(String name) throws RecordNotFoundException;

    Optional<Customer> findByEmail(String email) throws RecordNotFoundException;

    Optional<Customer> findByPhoneNumber(String phoneNumber) throws RecordNotFoundException;

    Customer create(Customer customer) throws RecordNotCreatedException;

    List<Customer> getAll() throws RecordNotFoundException;

    void saveAll(List<Customer> customers) throws RecordNotCreatedException;
}
