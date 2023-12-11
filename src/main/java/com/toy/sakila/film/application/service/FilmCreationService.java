package com.toy.sakila.film.application.service;

import com.toy.sakila.actor.application.port.out.ActorReadPort;
import com.toy.sakila.actor.domain.Actor;
import com.toy.sakila.category.application.port.out.CategoryReadPort;
import com.toy.sakila.category.domain.Category;
import com.toy.sakila.film.application.port.in.FilmCreationCommand;
import com.toy.sakila.film.application.port.in.FilmCreationUseCase;
import com.toy.sakila.film.application.port.out.FilmCreationPort;
import com.toy.sakila.film.domain.Film;
import com.toy.sakila.language.application.port.out.LanguageReadPort;
import com.toy.sakila.language.domain.Language;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FilmCreationService
        implements FilmCreationUseCase {

    private final LanguageReadPort languageReadPort;
    private final ActorReadPort actorReadPort;
    private final CategoryReadPort categoryReadPort;
    private final FilmCreationPort filmCreationPort;

    @Override
    @Transactional
    public Film create(FilmCreationCommand command) {
        List<Actor> actors = actorReadPort.findByIdIn(command.getActorIds());
        List<Category> categories = categoryReadPort.findByIdIn(command.getCategoryIds());
        Language language = languageReadPort.findById(Language.LanguageId.of(command.getLanguageId()));
        Language originalLanguage = languageReadPort.findById(Language.LanguageId.of(command.getOriginalLanguageId()));

        Film film = Film.builder()
                .title(command.getTitle())
                .description(command.getDescription())
                .releaseYear(command.getReleaseYear())
                .actors(actors)
                .categories(categories)
                .language(language)
                .originalLanguage(originalLanguage)
                .rentalRate(command.getRentalRate())
                .length(command.getLength())
                .replacementCost(command.getReplacementCost())
                .rating(command.getRating())
                .build();

        return filmCreationPort.create(film);
    }
}
