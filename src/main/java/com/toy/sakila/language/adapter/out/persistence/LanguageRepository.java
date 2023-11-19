package com.toy.sakila.language.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageJpaEntity, Long> {
}