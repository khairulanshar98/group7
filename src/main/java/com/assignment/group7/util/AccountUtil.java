package com.assignment.group7.util;

import com.assignment.group7.dto.RequestOfCreateAccount;
import com.assignment.group7.dto.exception.RecordNotCreatedException;
import com.assignment.group7.dto.exception.RecordNotFoundException;
import com.assignment.group7.entity.Customer;
import com.assignment.group7.entity.CustomerStatus;
import com.assignment.group7.service.impl.CustomerServiceImpl;
import com.assignment.group7.service.impl.CustomerStatusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
@Slf4j
@Component
public class AccountUtil {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private CustomerStatusServiceImpl customerStatusServiceImpl;
    public long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public Customer handleCreateAccount(RequestOfCreateAccount requestOfCreateAccount) throws RecordNotFoundException, RecordNotCreatedException {
        try {
            return this.customerServiceImpl.findByEmail(requestOfCreateAccount.getEmail()).get();
        } catch (Exception e) {
            log.error("handleCreateAccount, {}", e);
            CustomerStatus customerActive = this.customerStatusServiceImpl.findByName("Active").get();
            return this.customerServiceImpl.create(
                    Customer.builder()
                            .name(requestOfCreateAccount.getName())
                            .phoneNumber(requestOfCreateAccount.getPhoneNumber())
                            .email(requestOfCreateAccount.getEmail())
                            .address(requestOfCreateAccount.getAddress())
                            .city(requestOfCreateAccount.getCity())
                            .country(requestOfCreateAccount.getCountry())
                            .zipCode(requestOfCreateAccount.getZipCode())
                            .customerStatus(customerActive)
                            .updatedAt(new Date())
                            .build()
            );
        }
    }
}
