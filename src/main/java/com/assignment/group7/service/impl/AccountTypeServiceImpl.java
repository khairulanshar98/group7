package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountType;
import com.assignment.group7.repository.AccountTypeRepository;
import com.assignment.group7.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public Optional<AccountType> findByName(String name) throws RecordNotFoundException {
        try {
            return this.accountTypeRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public AccountType create(AccountType accountType) throws RecordNotCreatedException {
        try {
            if (this.accountTypeRepository.existsById(accountType.getAccountTypeId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.accountTypeRepository.save(accountType);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Record not created.").build();
        }
    }

    @Override
    public List<AccountType> getAll() throws RecordNotFoundException {
        try {
            return this.accountTypeRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<AccountType> accountTypes) throws RecordNotCreatedException {
        try {
            this.accountTypeRepository.saveAll(accountTypes);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Records not created.").build();
        }
    }
}
