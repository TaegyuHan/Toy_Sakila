package com.toy.sakila.staff.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffSpringDataJpaRepository extends JpaRepository<StaffJpaEntity, Byte> {
}