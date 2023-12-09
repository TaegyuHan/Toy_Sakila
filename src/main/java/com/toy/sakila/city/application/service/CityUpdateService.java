package com.toy.sakila.city.application.service;


import com.toy.sakila.city.application.port.in.CityUpdateCommand;
import com.toy.sakila.city.application.port.in.CityUpdateUseCase;
import com.toy.sakila.city.application.port.out.CityUpdatePort;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.country.application.port.out.CountryReadPort;
import com.toy.sakila.country.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CityUpdateService
        implements CityUpdateUseCase {

    private final CountryReadPort countryReadPort;
    private final CityUpdatePort cityUpdatePort;

    @Override
    public City update(City.CityId cityId, CityUpdateCommand command) {
        Country country = countryReadPort.findById(Country.CountryId.of(command.getCountryId()));
        City city = City.builder()
                .cityId(cityId)
                .city(command.getCity())
                .country(country)
                .build();
        return cityUpdatePort.update(city);
    }
}