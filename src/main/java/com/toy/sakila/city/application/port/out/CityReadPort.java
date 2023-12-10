package com.toy.sakila.city.application.port.out;

import com.toy.sakila.city.domain.City;

public interface CityReadPort {
    City findById(City.CityId id);
}
