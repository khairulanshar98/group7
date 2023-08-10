package com.assignment.group7.repository;

import com.assignment.group7.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Long> {
    Optional<TransactionStatus> findByName(String name);
}
