package com.toy.sakila.film.adapter.out.persistence;


import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.film.application.port.out.FilmCreationPort;
import com.toy.sakila.film.domain.Film;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class FilmPersistenceAdapter
        implements FilmCreationPort {

    private final FilmRepository filmRepository;
    private final FilmPersistenceMapper filmPersistenceMapper;

    @Override
    public Film create(Film domain) {
        return Optional.of(domain)
                .map(filmPersistenceMapper::mapToJpaEntity)
                .map(filmRepository::save)
                .map(filmPersistenceMapper::mapToDomainEntity)
                .orElseThrow();
    }
}