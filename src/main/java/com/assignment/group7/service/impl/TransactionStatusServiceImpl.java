package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.TransactionStatus;
import com.assignment.group7.repository.TransactionStatusRepository;
import com.assignment.group7.service.TransactionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TransactionStatusServiceImpl implements TransactionStatusService {
    @Autowired
    private TransactionStatusRepository transactionStatusRepository;
    @Override
    public Optional<TransactionStatus> findByName(String name) throws RecordNotFoundException {
        try {
            return this.transactionStatusRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public TransactionStatus create(TransactionStatus transactionStatus) throws RecordNotCreatedException {
        try {
            if (this.transactionStatusRepository.existsById(transactionStatus.getTransactionStatusId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.transactionStatusRepository.save(transactionStatus);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Record not created.").build();
        }
    }

    @Override
    public List<TransactionStatus> getAll() throws RecordNotFoundException {
        try {
            return this.transactionStatusRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<TransactionStatus> transactionStatuses) throws RecordNotCreatedException {
        try {
            this.transactionStatusRepository.saveAll(transactionStatuses);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Records not created.").build();
        }
    }
}
