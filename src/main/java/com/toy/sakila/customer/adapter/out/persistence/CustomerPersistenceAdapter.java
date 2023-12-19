package com.toy.sakila.customer.adapter.out.persistence;


import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.application.port.out.CustomerSavePort;
import com.toy.sakila.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
@Transactional
public class CustomerPersistenceAdapter
        implements CustomerSavePort, CustomerReadPort {

    private final SpringDataCustomerRepository springDataCustomerRepository;
    private final CustomerPersistenceMapper mapper;

    @Override
    public Customer save(Customer customer) {
        return Optional.of(customer)
                .map(mapper::mapToJpaEntity)
                .map(springDataCustomerRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Customer.CustomerId id) {
        return springDataCustomerRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}
