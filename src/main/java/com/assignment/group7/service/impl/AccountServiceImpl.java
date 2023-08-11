package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Account;
import com.assignment.group7.repository.AccountRepository;
import com.assignment.group7.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) throws RecordNotFoundException {
        try {
            return this.accountRepository.findByAccountNumber(accountNumber);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public Account create(Account account) throws RecordNotCreatedException {
        try {
            if (this.accountRepository.existsById(account.getAccountId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.accountRepository.save(account);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Record not created.").build();
        }
    }

    @Override
    public List<Account> getAll() throws RecordNotFoundException {
        try {
            return this.accountRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<Account> accounts) throws RecordNotCreatedException {
        try {
            this.accountRepository.saveAll(accounts);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Records not created.").build();
        }
    }
}
