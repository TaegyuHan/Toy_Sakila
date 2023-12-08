package com.toy.sakila.country.application.port.out;

import com.toy.sakila.country.domain.Country;

public interface CountryUpdatePort {
    Country update(Country country);
}
