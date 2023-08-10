package com.assignment.group7.repository;

import com.assignment.group7.entity.AccountCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCurrencyRepository extends JpaRepository<AccountCurrency, Long> {
    Optional<AccountCurrency> findByName(String name);
}
