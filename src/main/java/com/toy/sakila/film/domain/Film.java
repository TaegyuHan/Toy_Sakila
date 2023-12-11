package com.toy.sakila.film.domain;

import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.language.domain.Language;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter @Setter
@Builder
@AllArgsConstructor
public class Film {

    private final FilmId id;

    private String title;

    private String description;

    private Short releaseYear;

    private List<Actor> actors;

    private List<Category> categories;

    private Language language;

    private Language originalLanguage;

    private Short rentalDuration;

    private BigDecimal rentalRate;

    private Short length;

    private BigDecimal replacementCost;

    private EnumFilmRating rating;

    private List<EnumSpecialFeature> specialFeatures;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class FilmId {
        Long value;
    }
}