package com.toy.sakila.film.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.language.adapter.out.persistence.LanguagePersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

@RequiredArgsConstructor
@Mapper
public class FilmPersistenceMapper {

    private final LanguagePersistenceMapper languagePersistenceMapper;

    public FilmJpaEntity mapToJpaEntity(Film film) {
        return FilmJpaEntity.builder()
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .language(languagePersistenceMapper.mapToJpaEntity(film.getLanguage()))
                .originalLanguage(languagePersistenceMapper.mapToJpaEntity(film.getOriginalLanguage()))
                .rentalDuration(film.getRentalDuration())
                .rentalRate(film.getRentalRate())
                .length(film.getLength())
                .replacementCost(film.getReplacementCost())
                .rating(film.getRating())
                .specialFeatures(new HashSet<>(film.getSpecialFeatures()))
                .build();
    }
}