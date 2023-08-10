package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountType;
import com.assignment.group7.entity.TransactionType;

import java.util.List;
import java.util.Optional;

public interface TransactionTypeService {
    Optional<TransactionType> findByName(String name) throws RecordNotFoundException;

    TransactionType create(TransactionType transactionType) throws RecordNotCreatedException;

    List<TransactionType> getAll() throws RecordNotFoundException;

    void saveAll(List<TransactionType> transactionTypes) throws RecordNotCreatedException;
}
