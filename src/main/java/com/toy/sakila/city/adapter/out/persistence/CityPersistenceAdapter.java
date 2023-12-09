package com.toy.sakila.city.adapter.out.persistence;


import com.toy.sakila.city.application.port.out.CityCreationPort;
import com.toy.sakila.city.application.port.out.CityUpdatePort;
import com.toy.sakila.city.domain.City;
import com.toy.sakila.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class CityPersistenceAdapter
        implements CityCreationPort, CityUpdatePort {

    private final SpringDataCityRepository springDataCityRepository;
    private final CityPersistenceMapper cityPersistenceMapper;

    @Override
    public City.CityId create(City domain) {
        return Optional.of(domain)
                .map(cityPersistenceMapper::mapToJpaEntity)
                .map(springDataCityRepository::save)
                .map(CityJpaEntity::getCityId)
                .map(City.CityId::of)
                .orElseThrow();
    }

    @Override
    public City update(City domain) {
        Optional.of(domain)
                .map(cityPersistenceMapper::mapToJpaEntity)
                .map(springDataCityRepository::save)
                .map(cityPersistenceMapper::mapToDomainEntity)
                .orElseThrow();
        return null;
    }
}