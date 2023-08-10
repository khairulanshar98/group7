package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> findByAccountNumber(String accountNumber) throws RecordNotFoundException;

    Account create(Account account) throws RecordNotCreatedException;

    List<Account> getAll() throws RecordNotFoundException;

    void saveAll(List<Account> accounts) throws RecordNotCreatedException;
}
