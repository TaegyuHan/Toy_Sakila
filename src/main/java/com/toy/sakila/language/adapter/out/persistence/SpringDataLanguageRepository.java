package com.toy.sakila.language.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataLanguageRepository extends JpaRepository<LanguageJpaEntity, Long> {
}