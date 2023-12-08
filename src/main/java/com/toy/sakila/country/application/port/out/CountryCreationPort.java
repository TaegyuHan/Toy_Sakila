package com.toy.sakila.country.application.port.out;

import com.toy.sakila.country.domain.Country;

public interface CountryCreationPort {
    Country.CountryId create(Country country);
}
