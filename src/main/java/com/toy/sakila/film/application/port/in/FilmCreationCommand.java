package com.toy.sakila.film.application.port.in;


import com.toy.sakila.film.domain.EnumFilmRating;
import com.toy.sakila.film.domain.EnumSpecialFeature;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class FilmCreationCommand {
    private String title;

    private String description;

    private Short releaseYear;

    private List<Long> actorIds;

    private List<Long> categoryIds;

    private Long languageId;

    private Long originalLanguageId;

    private Short rentalDuration;

    private BigDecimal rentalRate;

    private Short length;

    private BigDecimal replacementCost;

    private EnumFilmRating rating;

    private List<EnumSpecialFeature> specialFeatures;
}