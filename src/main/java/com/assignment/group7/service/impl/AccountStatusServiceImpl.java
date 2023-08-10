package com.assignment.group7.service.impl;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.AccountStatus;
import com.assignment.group7.repository.AccountStatusRepository;
import com.assignment.group7.service.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {
    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Override
    public Optional<AccountStatus> findByName(String name) throws RecordNotFoundException {
        try {
            return this.accountStatusRepository.findByName(name);
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public AccountStatus create(AccountStatus accountStatus) throws RecordNotCreatedException {
        try {
            if (this.accountStatusRepository.existsById(accountStatus.getAccountStatusId())) {
                throw RecordNotCreatedException.builder().message("Duplicate Id.").build();
            } else {
                return this.accountStatusRepository.save(accountStatus);
            }
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Record not created.").build();
        }
    }

    @Override
    public List<AccountStatus> getAll() throws RecordNotFoundException {
        try {
            return this.accountStatusRepository.findAll();
        } catch (NoSuchElementException e) {
            throw RecordNotFoundException.builder().cause(e).message("Not record found.").build();
        }
    }

    @Override
    public void saveAll(List<AccountStatus> accountStatuses) throws RecordNotCreatedException {
        try {
            this.accountStatusRepository.saveAll(accountStatuses);
        } catch (DataAccessException e) {
            throw RecordNotCreatedException.builder().cause(e).message("Records not created.").build();
        }
    }
}
