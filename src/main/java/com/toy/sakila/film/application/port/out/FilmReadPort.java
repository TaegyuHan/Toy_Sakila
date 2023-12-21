package com.toy.sakila.film.application.port.out;

import com.toy.sakila.film.domain.Film;

public interface FilmReadPort {
    Film findById(Film.FilmId id);
}
