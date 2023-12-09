package com.toy.sakila.city.application.port.out;

import com.toy.sakila.city.domain.City;

public interface CityCreationPort {
    City.CityId create(City domain);
}