package com.toy.sakila.city.application.port.in;

import com.toy.sakila.city.domain.City;

public interface CityUpdateUseCase {
    City update(City.CityId cityId, CityUpdateCommand command);
}