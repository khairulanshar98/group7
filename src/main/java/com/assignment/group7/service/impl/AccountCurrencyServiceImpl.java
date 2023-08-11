package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountCurrency;
import com.assignment.group7.repository.AccountCurrencyRepository;
import com.assignment.group7.service.AccountCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountCurrencyServiceImpl implements AccountCurrencyService {
    @Autowired
    private AccountCurrencyRepository accountCurrencyRepository;

    @Override
    public Optional<AccountCurrency> findByName(String name) throws RecordNotFoundException {
        try {
            return this.accountCurrencyRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public AccountCurrency create(AccountCurrency accountCurrency) throws RecordNotCreatedException {
        try {
            if (this.accountCurrencyRepository.existsById(accountCurrency.getAccountCurrencyId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.accountCurrencyRepository.save(accountCurrency);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Record not created.").build();
        }
    }

    @Override
    public List<AccountCurrency> getAll() throws RecordNotFoundException {
        try {
            return this.accountCurrencyRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e.getCause()).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<AccountCurrency> accountCurrencies) throws RecordNotCreatedException {
        try {
            this.accountCurrencyRepository.saveAll(accountCurrencies);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e.getCause()).message("Records not created.").build();
        }
    }
}
