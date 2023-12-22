package com.toy.sakila.inventory.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventorySpringDataRepository extends JpaRepository<InventoryJpaEntity, Integer> {
}
