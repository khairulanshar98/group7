package com.assignment.group7.dto;

import lombok.Data;

@Data
public class RequestOfCreateAccount {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private long zipCode;
    private long amount;
}
