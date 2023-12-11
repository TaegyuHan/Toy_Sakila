package com.toy.sakila.city.adapter.out.persistence;


import com.toy.sakila.city.application.port.out.CityReadPort;
import com.toy.sakila.city.application.port.out.CitySavePort;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
@PersistenceAdapter
public class CityPersistenceAdapter
        implements CitySavePort, CityReadPort {

    private final SpringDataCityRepository springDataCityRepository;
    private final CityPersistenceMapper mapper;

    @Override
    public City save(City domain) {
        return Optional.of(domain)
                .map(mapper::mapToJpaEntity)
                .map(springDataCityRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public City findById(City.CityId id) {
        return springDataCityRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}