package com.toy.sakila.rental.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRentalRepository extends JpaRepository<RentalJpaEntity, Integer> {
}
