package com.toy.sakila.country.application.port.out;

import com.toy.sakila.country.domain.Country;

public interface CountryReadPort {
    Country findById(Country.CountryId id);
}