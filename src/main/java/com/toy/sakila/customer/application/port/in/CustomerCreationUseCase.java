package com.toy.sakila.customer.application.port.in;

import com.toy.sakila.common.UseCase;
import com.toy.sakila.customer.domain.Customer;

@UseCase
public interface CustomerCreationUseCase {
    Customer create(CustomerCreationCommand command);
}