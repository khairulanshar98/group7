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
public class CustomerStatus {
    private final Date createdAt = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerStatusId;
    @Column(unique = true)
    private String name;
    private Date updatedAt;
    private boolean isActive;
    @OneToMany(mappedBy = "customerStatus")
    @JsonIgnore
    private List<Customer> customers;
}
