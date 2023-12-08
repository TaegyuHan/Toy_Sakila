package com.toy.sakila.country.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryJpaEntity, Integer> {
}
