package com.toy.sakila.city.application.service;


import com.toy.sakila.city.application.port.in.CityCreationCommand;
import com.toy.sakila.city.application.port.in.CityCreationUseCase;
import com.toy.sakila.city.application.port.out.CityCreationPort;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.country.application.port.out.CountryReadPort;
import com.toy.sakila.country.domain.Country;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityCreationService
        implements CityCreationUseCase {

    private final CountryReadPort countryReadPort;
    private final CityCreationPort cityCreationPort;

    @Override
    @Transactional
    public City.CityId create(CityCreationCommand command) {
        Country country = countryReadPort.findById(Country.CountryId.of(command.getCountryId()));
        City city = City.builder()
                .city(command.getCity())
                .country(country)
                .build();
        return cityCreationPort.create(city);
    }
}