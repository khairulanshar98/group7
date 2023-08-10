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
public class AccountType {
    private final Date createdAt = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountTypeId;
    @Column(unique=true)
    private String name;
    private Date updatedAt;

    @OneToMany(mappedBy = "accountType")
    @JsonIgnore
    private List<Account> accounts;
}
