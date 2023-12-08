package com.toy.sakila.country.application.service;


import com.toy.sakila.country.application.port.in.CountryCreationCommand;
import com.toy.sakila.country.application.port.in.CountryCreationUseCase;
import com.toy.sakila.country.application.port.out.CountryCreationPort;
import com.toy.sakila.country.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryCreationService implements CountryCreationUseCase {

    private final CountryCreationPort countryCreationPort;

    @Override
    public Country.CountryId create(CountryCreationCommand command) {
        Country country = Country.builder()
                .country(command.getCountry())
                .build();
        return countryCreationPort.create(country);
    }
}
