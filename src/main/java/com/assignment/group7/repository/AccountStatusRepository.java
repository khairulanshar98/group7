package com.assignment.group7.repository;

import com.assignment.group7.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {
    Optional<AccountStatus> findByName(String name);
}