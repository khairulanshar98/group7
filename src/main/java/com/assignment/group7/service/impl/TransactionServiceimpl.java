package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Transaction;
import com.assignment.group7.repository.TransactionRepository;
import com.assignment.group7.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionServiceimpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Optional<Transaction> findByTransactionType(String transactionType) throws RecordNotFoundException {
        try {
            return this.transactionRepository.findByTransactionType(transactionType);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public Optional<Transaction> findByTransactionStatus(String transactionStatus) throws RecordNotFoundException {
        try {
            return this.transactionRepository.findByTransactionStatus(transactionStatus);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public Transaction create(Transaction transaction) throws RecordNotCreatedException {
        try {
            if (this.transactionRepository.existsById(transaction.getTransactionId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.transactionRepository.save(transaction);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Record not created.").build();
        }
    }

    @Override
    public List<Transaction> getAll() throws RecordNotFoundException {
        try {
            return this.transactionRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<Transaction> transactions) throws RecordNotCreatedException {
        try {
            this.transactionRepository.saveAll(transactions);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Records not created.").build();
        }
    }
}
