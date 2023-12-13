package com.toy.sakila.customer.application.port.out;

import com.toy.sakila.customer.domain.Customer;

public interface CustomerReadPort {
    Customer findById(Customer.CustomerId id);
}
