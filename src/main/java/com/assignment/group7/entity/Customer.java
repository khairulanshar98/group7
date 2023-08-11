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
public class Customer {
    private final Date createdAt = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;
    private String name;
    private Date updatedAt;
    @Column(unique = true,  nullable = false)
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private long zipCode;

    @ManyToOne
    @JoinColumn(name = "customer_status_id", nullable = false)
    private CustomerStatus customerStatus;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Account> accounts;
}
