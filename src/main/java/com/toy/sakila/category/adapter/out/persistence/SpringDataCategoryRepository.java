package com.toy.sakila.category.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SpringDataCategoryRepository extends JpaRepository<CategoryJpaEntity, Byte> {
    List<CategoryJpaEntity> findByCategoryIdIn(List<Byte> ids);
}