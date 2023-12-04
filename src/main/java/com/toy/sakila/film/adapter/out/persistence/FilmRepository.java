package com.toy.sakila.film.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmJpaEntity, Long> {
}