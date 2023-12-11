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

    public FilmJpaEntity mapToJpaEntity(Film domain) {
        return FilmJpaEntity.builder()
                .title(domain.getTitle())
                .description(domain.getDescription())
                .releaseYear(domain.getReleaseYear())
                .language(languagePersistenceMapper.mapToJpaEntity(domain.getLanguage()))
                .originalLanguage(languagePersistenceMapper.mapToJpaEntity(domain.getOriginalLanguage()))
                .rentalDuration(domain.getRentalDuration())
                .rentalRate(domain.getRentalRate())
                .length(domain.getLength())
                .replacementCost(domain.getReplacementCost())
                .rating(domain.getRating())
                .specialFeatures(new HashSet<>(domain.getSpecialFeatures()))
                .build();
    }

    public Film mapToDomainEntity(FilmJpaEntity entity) {
        return Film.builder()
                .id(Film.FilmId.of(entity.getFilmId()))
                .title(entity.getTitle())
                .description(entity.getDescription())
                .releaseYear(entity.getReleaseYear())
                .language(languagePersistenceMapper.mapToDomainEntity(entity.getLanguage()))
                .originalLanguage(languagePersistenceMapper.mapToDomainEntity(entity.getOriginalLanguage()))
                .rentalDuration(entity.getRentalDuration())
                .rentalRate(entity.getRentalRate())
                .length(entity.getLength())
                .replacementCost(entity.getReplacementCost())
                .rating(entity.getRating())
                .specialFeatures(entity.getSpecialFeatures().stream().toList())
                .build();
    }
}