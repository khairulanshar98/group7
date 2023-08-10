package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Optional<Transaction> findByTransactionType(String transactionType) throws RecordNotFoundException;

    Optional<Transaction> findByTransactionStatus(String transactionStatus) throws RecordNotFoundException;

    Transaction create(Transaction transaction) throws RecordNotCreatedException;

    List<Transaction> getAll() throws RecordNotFoundException;

    void saveAll(List<Transaction> transactions) throws RecordNotCreatedException;

}
