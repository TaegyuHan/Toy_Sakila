package com.toy.sakila.inventory.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventorySpringDataJpaRepository extends JpaRepository<InventoryJpaEntity, Integer> {
}
