package com.toy.sakila.film.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataFilmRepository extends JpaRepository<FilmJpaEntity, Long> {
}