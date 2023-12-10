package com.toy.sakila.city.application.port.in;

import com.toy.sakila.city.domain.City;

public interface CityCreationUseCase {
    City create(CityCreationCommand command);
}