package com.assignment.group7.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Account {
    private final Date createdAt = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;
    private long accountNumber;
    private long balance;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "account_currency_id", nullable = false)
    private AccountCurrency accountCurrency;

    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "account_status_id", nullable = false)
    private AccountStatus accountStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    //    @OneToMany(mappedBy = "sourceAccount")
//    @JsonIgnore
//    private List<Transaction> sourceTransactions;
//
    @OneToMany(mappedBy = "targetAccount")
    @JsonIgnore
    private List<Transaction> targetTransactions;
}
