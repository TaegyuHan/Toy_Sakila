package com.toy.sakila.country.application.port.in;

import com.toy.sakila.common.UseCase;
import com.toy.sakila.country.domain.Country;

@UseCase
public interface CountryCreationUseCase {
    Country.CountryId create(CountryCreationCommand command);
}
