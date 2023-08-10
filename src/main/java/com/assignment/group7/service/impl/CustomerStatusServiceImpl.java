package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.CustomerStatus;
import com.assignment.group7.repository.CustomerStatusRepository;
import com.assignment.group7.service.CustomerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService {
    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Override
    public Optional<CustomerStatus> findByName(String name) throws RecordNotFoundException {
        try {
            return this.customerStatusRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public CustomerStatus create(CustomerStatus customerStatus) throws RecordNotCreatedException {
        try {
            if (this.customerStatusRepository.existsById(customerStatus.getCustomerStatusId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.customerStatusRepository.save(customerStatus);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Record not created.").build();
        }
    }

    @Override
    public List<CustomerStatus> getAll() throws RecordNotFoundException {
        try {
            return this.customerStatusRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<CustomerStatus> customerStatuses) throws RecordNotCreatedException {
        try {
            this.customerStatusRepository.saveAll(customerStatuses);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Records not created.").build();
        }
    }
}
