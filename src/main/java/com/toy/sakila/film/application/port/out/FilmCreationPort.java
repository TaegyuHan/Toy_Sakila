package com.toy.sakila.film.application.port.out;

import com.toy.sakila.film.domain.Film;

public interface FilmCreationPort {
    Film create(Film domain);
}