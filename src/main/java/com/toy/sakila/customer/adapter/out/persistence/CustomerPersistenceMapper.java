package com.toy.sakila.customer.adapter.out.persistence;


import com.toy.sakila.common.Mapper;
import com.toy.sakila.customer.domain.Customer;

@Mapper
public class CustomerPersistenceMapper {
    public CustomerJpaEntity mapToJpaEntity(Customer domain) {
        return CustomerJpaEntity.builder()
                .customerId(domain.getId().getValue())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .email(domain.getEmail())
                .addressId(domain.getAddress().getId().getValue())
                .active(domain.isActive())
                .build();
    }

    public Customer mapToDomainEntity(CustomerJpaEntity entity) {
        return Customer.builder()
                .id(Customer.CustomerId.of(entity.getCustomerId()))
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .active(entity.isActive())
                .lastUpdate(entity.getLastUpdate())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}