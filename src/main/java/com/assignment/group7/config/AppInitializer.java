package com.assignment.group7.config;

import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.*;
import com.assignment.group7.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class AppInitializer implements CommandLineRunner {
    @Autowired
    private AccountTypeServiceImpl accountTypeServiceImpl;
    @Autowired
    private AccountCurrencyServiceImpl accountCurrencyServiceImpl;
    @Autowired
    private CustomerStatusServiceImpl customerStatusServiceImpl;
    @Autowired
    private AccountStatusServiceImpl accountStatusServiceImpl;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private AccountServiceImpl accountServiceImpl;
    @Autowired
    private TransactionTypeServiceImpl transactionTypeServiceImpl;
    @Autowired
    private TransactionStatusServiceImpl transactionStatusServiceImpl;
    @Autowired
    private TransactionServiceimpl transactionServiceimpl;

    @Override
    public void run(String... args) throws Exception {
        log.info("AppInitializer");
        try {
            loadAccountType();
            loadAccountCurrency();
            loadCustomerStatus();
            loadCustomer();
            loadAccountStatus();
            loadTransactionType();
            loadTransactionStatus();
            loadAccount();
        } catch (Exception e) {
            log.error("run, {}", e);
        }
    }

    private void loadAccountType() throws RecordNotCreatedException {
        this.accountTypeServiceImpl.saveAll(
                List.of(AccountType.builder().name("Saving").updatedAt(new Date()).build(),
                        AccountType.builder().name("Current").updatedAt(new Date()).build()
                ));
    }

    private void loadAccountCurrency() throws RecordNotCreatedException {
        this.accountCurrencyServiceImpl.saveAll(
                List.of(AccountCurrency.builder().name("SGD").updatedAt(new Date()).build(),
                        AccountCurrency.builder().name("USD").updatedAt(new Date()).build(),
                        AccountCurrency.builder().name("INR").updatedAt(new Date()).build(),
                        AccountCurrency.builder().name("IDR").updatedAt(new Date()).build()
                ));
    }

    private void loadTransactionType() throws RecordNotCreatedException {
        this.transactionTypeServiceImpl.saveAll(
                List.of(TransactionType.builder().name("Open Account").updatedAt(new Date()).build(),
                        TransactionType.builder().name("Deposit").updatedAt(new Date()).build(),
                        TransactionType.builder().name("Withdraw").updatedAt(new Date()).build()
                ));
    }

    private void loadTransactionStatus() throws RecordNotCreatedException {
        this.transactionStatusServiceImpl.saveAll(
                List.of(
                        TransactionStatus.builder().name("Created").updatedAt(new Date()).build(),
                        TransactionStatus.builder().name("Validated").updatedAt(new Date()).build(),
                        TransactionStatus.builder().name("Rejected").updatedAt(new Date()).build()
                ));
    }

    private void loadCustomerStatus() throws RecordNotCreatedException {
        this.customerStatusServiceImpl.saveAll(
                List.of(
                        CustomerStatus.builder().isActive(true).name("Active").updatedAt(new Date()).build(),
                        CustomerStatus.builder().isActive(false).name("Not Active").updatedAt(new Date()).build()
                ));
    }

    private void loadAccountStatus() throws RecordNotCreatedException {
        this.accountStatusServiceImpl.saveAll(
                List.of(
                        AccountStatus.builder().isActive(true).name("Active").updatedAt(new Date()).build(),
                        AccountStatus.builder().isActive(false).name("Not Active").updatedAt(new Date()).build()
                ));
    }

    private void loadCustomer() throws RecordNotCreatedException, RecordNotFoundException {
        CustomerStatus active = this.customerStatusServiceImpl.findByName("Active").get();
        CustomerStatus notActive = this.customerStatusServiceImpl.findByName("Not Active").get();
        this.customerServiceImpl.saveAll(
                List.of(
                        Customer.builder()
                                .name("Khairul Anshar")
                                .phoneNumber("+65123456")
                                .email("khairul@mail.com")
                                .address("Street xyx")
                                .city("Singapore")
                                .country("Singapore")
                                .zipCode(555555)
                                .customerStatus(active)
                                .updatedAt(new Date())
                                .build(),
                        Customer.builder()
                                .name("John Doe")
                                .phoneNumber("+654545345")
                                .email("john@mail.com")
                                .address("Street zzz")
                                .city("Singapore")
                                .country("Singapore")
                                .zipCode(444444)
                                .customerStatus(notActive)
                                .updatedAt(new Date())
                                .build()
                ));
    }

    private void loadAccount() throws RecordNotCreatedException, RecordNotFoundException {
        AccountStatus active = this.accountStatusServiceImpl.findByName("Active").get();
        AccountType accountType = this.accountTypeServiceImpl.findByName("Saving").get();
        AccountCurrency SGD = this.accountCurrencyServiceImpl.findByName("SGD").get();
        Customer customer = this.customerServiceImpl.findByEmail("khairul@mail.com").get();
        long amount = 1000;
        Account account = this.accountServiceImpl.create(Account.builder()
                .accountNumber("88888888888")
                .customer(customer)
                .accountStatus(active)
                .accountType(accountType)
                .accountCurrency(SGD)
                .updatedAt(new Date())
                .balance(amount)
                .build());

        TransactionStatus transactionStatus = this.transactionStatusServiceImpl.findByName("Validated").get();
        TransactionType transactionType = this.transactionTypeServiceImpl.findByName("Open Account").get();
        this.transactionServiceimpl.create(Transaction.builder()
                .amount(amount)
                .updatedAt(new Date())
                .transactionType(transactionType)
                .transactionStatus(transactionStatus)
                .targetAccount(account)
                .build());
    }
}
