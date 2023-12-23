package com.toy.sakila.film.adapter.out.persistence;


import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.film.application.port.out.FilmCreationPort;
import com.toy.sakila.film.application.port.out.FilmReadPort;
import com.toy.sakila.film.domain.Film;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class FilmPersistenceAdapter
        implements FilmCreationPort, FilmReadPort {

    private final SpringDataFilmRepository SpringDataFilmRepository;
    private final FilmPersistenceMapper mapper;

    @Override
    public Film create(Film domain) {
        return Optional.of(domain)
                .map(mapper::mapToJpaEntity)
                .map(SpringDataFilmRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Film findById(Film.FilmId id) {
        return SpringDataFilmRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}