package com.toy.sakila.country.application.service;


import com.toy.sakila.country.application.port.in.CountryUpdateCommand;
import com.toy.sakila.country.application.port.in.CountryUpdateUseCase;
import com.toy.sakila.country.application.port.out.CountryUpdatePort;
import com.toy.sakila.country.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CountryUpdateService implements CountryUpdateUseCase {

    private final CountryUpdatePort countryUpdatePort;

    @Override
    public Country update(Country.CountryId id, CountryUpdateCommand command) {
        Country country = Country.builder()
                .id(id)
                .country(command.getCountry())
                .build();
        return countryUpdatePort.update(country);
    }
}