package com.assignment.group7.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Transaction {
    private final Date createdAt = new Date();
    private final Date transactionDate = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private long amount;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "transaction_status_id", nullable = false)
    private TransactionStatus transactionStatus;

//    @ManyToMany
//    @JoinColumn(name = "account_id", nullable = true)
//    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true)
    private Account targetAccount;
}
