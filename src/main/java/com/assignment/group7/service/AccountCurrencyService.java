package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountCurrency;

import java.util.List;
import java.util.Optional;

public interface AccountCurrencyService {
    Optional<AccountCurrency> findByName(String name) throws RecordNotFoundException;

    AccountCurrency create(AccountCurrency accountCurrency) throws RecordNotCreatedException;

    List<AccountCurrency> getAll() throws RecordNotFoundException;

    void saveAll(List<AccountCurrency> accountCurrencies) throws RecordNotCreatedException;
}
