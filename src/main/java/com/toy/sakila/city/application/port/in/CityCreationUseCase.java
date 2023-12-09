package com.toy.sakila.city.application.port.in;

import com.toy.sakila.city.domain.City;

public interface CityCreationUseCase {
    City.CityId create(CityCreationCommand command);
}