package com.toy.sakila.customer.application.port.in;

import com.toy.sakila.common.UseCase;
import com.toy.sakila.customer.domain.Customer;

@UseCase
public interface CustomerUpdateUseCase {
    Customer update(Customer.CustomerId id, CustomerUpdateCommand command);
}
