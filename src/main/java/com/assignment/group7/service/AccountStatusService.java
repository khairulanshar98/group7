package com.assignment.group7.service;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountStatus;

import java.util.List;
import java.util.Optional;

public interface AccountStatusService {
    Optional<AccountStatus> findByName(String name) throws RecordNotFoundException;

    AccountStatus create(AccountStatus accountStatus) throws RecordNotCreatedException;

    List<AccountStatus> getAll() throws RecordNotFoundException;

    void saveAll(List<AccountStatus> accountStatuses) throws RecordNotCreatedException;
}
