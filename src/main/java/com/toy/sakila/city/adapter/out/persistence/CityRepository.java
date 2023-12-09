package com.toy.sakila.city.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityJpaEntity, Long> {
}