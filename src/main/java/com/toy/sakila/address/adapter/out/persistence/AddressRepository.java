package com.toy.sakila.address.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressJpaEntity, Long> {
}