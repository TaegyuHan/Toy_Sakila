package com.toy.sakila.store.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreJpaEntity, Byte> {
}
