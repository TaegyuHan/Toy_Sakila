package com.toy.sakila.category.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
}