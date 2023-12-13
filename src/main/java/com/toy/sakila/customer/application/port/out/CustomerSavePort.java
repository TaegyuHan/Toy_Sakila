package com.toy.sakila.customer.application.port.out;

import com.toy.sakila.customer.domain.Customer;

public interface CustomerSavePort {
    Customer save(Customer customer);
}
