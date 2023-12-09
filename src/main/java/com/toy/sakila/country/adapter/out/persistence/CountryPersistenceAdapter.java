package com.toy.sakila.country.adapter.out.persistence;

import com.toy.sakila.common.PersistenceAdapter;
import com.toy.sakila.country.application.port.out.CountryCreationPort;
import com.toy.sakila.country.application.port.out.CountryReadPort;
import com.toy.sakila.country.application.port.out.CountryUpdatePort;
import com.toy.sakila.country.domain.Country;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class CountryPersistenceAdapter
        implements CountryCreationPort, CountryReadPort, CountryUpdatePort {

    private final SpringDataCountryRepository springDataRepository;
    private final CountryPersistenceMapper mapper;

    @Override
    public Country.CountryId create(Country country) {
        return Optional.of(country)
                .map(mapper::mapToJpaEntity)
                .map(springDataRepository::save)
                .map(CountryJpaEntity::getCountryId)
                .map(Country.CountryId::of)
                .orElseThrow();
    }

    @Override
    public Country findById(Country.CountryId id) {
        return springDataRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Country update(Country country) {
        return springDataRepository.findById(country.getId().getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}