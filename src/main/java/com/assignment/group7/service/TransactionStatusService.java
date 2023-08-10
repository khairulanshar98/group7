package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.TransactionStatus;

import java.util.List;
import java.util.Optional;

public interface TransactionStatusService {
    Optional<TransactionStatus> findByName(String name) throws RecordNotFoundException;

    TransactionStatus create(TransactionStatus transactionStatus) throws RecordNotCreatedException;

    List<TransactionStatus> getAll() throws RecordNotFoundException;

    void saveAll(List<TransactionStatus> transactionStatuses) throws RecordNotCreatedException;

}
