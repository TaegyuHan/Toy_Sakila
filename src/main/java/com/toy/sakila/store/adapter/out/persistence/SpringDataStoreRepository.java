package com.toy.sakila.store.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataStoreRepository extends JpaRepository<StoreJpaEntity, Byte> {
}
