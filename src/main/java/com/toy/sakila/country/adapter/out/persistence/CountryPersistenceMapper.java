package com.toy.sakila.country.adapter.out.persistence;

import com.toy.sakila.common.Mapper;
import com.toy.sakila.country.domain.Country;

@Mapper
public class CountryPersistenceMapper {
    public CountryJpaEntity mapToJpaEntity(Country domain) {
        return CountryJpaEntity.builder()
                .countryId(domain.getId().getValue())
                .country(domain.getCountry())
                .build();
    }

    public Country mapToDomainEntity(CountryJpaEntity entity) {
        return Country.builder()
                .id(Country.CountryId.of(entity.getCountryId()))
                .country(entity.getCountry())
                .build();
    }
}
