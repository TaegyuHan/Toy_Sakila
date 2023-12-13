package com.toy.sakila.customer.application.service;

import com.toy.sakila.customer.application.port.in.CustomerUpdateCommand;
import com.toy.sakila.customer.application.port.in.CustomerUpdateUseCase;
import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.application.port.out.CustomerSavePort;
import com.toy.sakila.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerUpdateService implements CustomerUpdateUseCase {

    private final CustomerSavePort customerSavePort;
    private final CustomerReadPort customerReadPort;

    @Override
    public Customer update(CustomerUpdateCommand command) {


        return null;
    }
}