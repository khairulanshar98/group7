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
public class AccountCurrency {
    private final Date createdAt = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountCurrencyId;
    @Column(unique=true)
    private String name;
    private Date updatedAt;

    @OneToMany(mappedBy = "accountCurrency")
    @JsonIgnore
    private List<Account> accounts;
}
