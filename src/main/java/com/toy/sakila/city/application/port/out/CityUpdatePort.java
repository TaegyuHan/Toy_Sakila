package com.toy.sakila.city.application.port.out;

import com.toy.sakila.city.domain.City;

public interface CityUpdatePort {
    City update(City city);
}