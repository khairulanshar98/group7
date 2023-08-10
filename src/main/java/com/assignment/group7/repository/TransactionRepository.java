package com.assignment.group7.repository;

import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionType(String transactionType) throws RecordNotFoundException;

    Optional<Transaction> findByTransactionStatus(String transactionStatus) throws RecordNotFoundException;

}
