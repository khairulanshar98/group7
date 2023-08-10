package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.CustomerStatus;

import java.util.List;
import java.util.Optional;

public interface CustomerStatusService {
    Optional<CustomerStatus> findByName(String name) throws RecordNotFoundException;

    CustomerStatus create(CustomerStatus customerStatus) throws RecordNotCreatedException;

    List<CustomerStatus> getAll() throws RecordNotFoundException;

    void saveAll(List<CustomerStatus> customerStatuses) throws RecordNotCreatedException;
}
