package com.toy.sakila.film.application.port.in;

import com.toy.sakila.film.domain.Film;

public interface FilmCreationUseCase {
    Film.FilmId create(FilmCreationCommand command);
}