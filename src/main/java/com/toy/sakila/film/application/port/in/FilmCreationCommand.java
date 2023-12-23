package com.toy.sakila.film.application.port.in;


import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.category.domain.Category;
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
public class FilmCreationCommand { String title;
    String description;
    Short releaseYear;
    List<Actor.ActorId> actorIds;
    List<Category.CategoryId> categoryIds;
    Byte languageId;
    Byte originalLanguageId;
    Short rentalDuration;
    BigDecimal rentalRate;
    Short length;
    BigDecimal replacementCost;
    EnumFilmRating rating;
    List<EnumSpecialFeature> specialFeatures;
}