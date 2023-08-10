package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountType;
import com.assignment.group7.entity.TransactionType;
import com.assignment.group7.repository.AccountTypeRepository;
import com.assignment.group7.repository.TransactionTypeRepository;
import com.assignment.group7.service.AccountTypeService;
import com.assignment.group7.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public Optional<TransactionType> findByName(String name) throws RecordNotFoundException {
        try {
            return this.transactionTypeRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public TransactionType create(TransactionType transactionType) throws RecordNotCreatedException {
        try {
            if (this.transactionTypeRepository.existsById(transactionType.getTransactionTypeId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.transactionTypeRepository.save(transactionType);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Record not created.").build();
        }
    }

    @Override
    public List<TransactionType> getAll() throws RecordNotFoundException {
        try {
            return this.transactionTypeRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<TransactionType> transactionTypes) throws RecordNotCreatedException {
        try {
            this.transactionTypeRepository.saveAll(transactionTypes);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Records not created.").build();
        }
    }
}
