package com.assignment.group7.controller;

import com.assignment.group7.dto.ErrorPropsResponse;
import com.assignment.group7.dto.RequestOfCreateAccount;
import com.assignment.group7.dto.SuccessPropsResponse;
import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.entity.*;
import com.assignment.group7.service.impl.*;
import com.assignment.group7.util.AccountUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/account/v1")
@Tag(name = "Account API V1", description = "Account Related API")
public class AccountController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private AccountTypeServiceImpl accountTypeServiceImpl;
    @Autowired
    private AccountCurrencyServiceImpl accountCurrencyServiceImpl;
    @Autowired
    private AccountStatusServiceImpl accountStatusServiceImpl;
    @Autowired
    private CustomerStatusServiceImpl customerStatusServiceImpl;
    @Autowired
    private AccountServiceImpl accountServiceImpl;
    @Autowired
    private TransactionTypeServiceImpl transactionTypeServiceImpl;
    @Autowired
    private TransactionStatusServiceImpl transactionStatusServiceImpl;
    @Autowired
    private TransactionServiceimpl transactionServiceimpl;
    @Autowired
    private AccountUtil accountUtil;


    @PostMapping("/create")
    @Operation(summary = "Open Account", description = "Open new Account, it will check if customer is existing customer by using email address, otherwise it will create new customer.")
    public ResponseEntity<?> createCustomer(@RequestBody RequestOfCreateAccount requestOfCreateAccount) {
        try {
            Customer customer = this.accountUtil.handleCreateAccount(requestOfCreateAccount);
            AccountStatus accountActive = this.accountStatusServiceImpl.findByName("Active").get();
            AccountType accountType = this.accountTypeServiceImpl.findByName("Saving").get();
            AccountCurrency SGD = this.accountCurrencyServiceImpl.findByName("SGD").get();
            Account account = this.accountServiceImpl.create(Account.builder()
                    .accountNumber(accountUtil.generateRandom(12))
                    .customer(customer)
                    .accountStatus(accountActive)
                    .accountType(accountType)
                    .accountCurrency(SGD)
                    .updatedAt(new Date())
                    .balance(requestOfCreateAccount.getAmount())
                    .build());
            TransactionStatus transactionStatus = this.transactionStatusServiceImpl.findByName("Validated").get();
            TransactionType transactionType = this.transactionTypeServiceImpl.findByName("Open Account").get();
            Transaction transaction = this.transactionServiceimpl.create(Transaction.builder()
                    .amount(requestOfCreateAccount.getAmount())
                    .updatedAt(new Date())
                    .transactionType(transactionType)
                    .transactionStatus(transactionStatus)
                    .targetAccount(account)
                    .build());
            return ResponseEntity.status(HttpStatus.OK).body(
                    SuccessPropsResponse.builder()
                            .statusCode(HttpStatus.CREATED)
                            .message("New Account Created")
                            .body(Map.of("account",account,"customer",customer,"transaction",transaction))
                            .build()
            );
        } catch (RecordNotCreatedException e) {
            log.error("createCustomer RecordNotCreatedException, {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorPropsResponse.builder()
                            .cause(e.getCause())
                            .statusCode(HttpStatus.BAD_REQUEST)
                            .message(e.getMessage())
                            .build());
        } catch (Exception e) {
            log.error("createCustomer Exception, {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorPropsResponse.builder()
                            .cause(e.getCause())
                            .statusCode(HttpStatus.BAD_REQUEST)
                            .message(e.getMessage())
                            .build());
        }
    }
}
