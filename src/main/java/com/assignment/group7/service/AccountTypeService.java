package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {
    Optional<AccountType> findByName(String name) throws RecordNotFoundException;

    AccountType create(AccountType accountType) throws RecordNotCreatedException;

    List<AccountType> getAll() throws RecordNotFoundException;

    void saveAll(List<AccountType> accountTypes) throws RecordNotCreatedException;
}
