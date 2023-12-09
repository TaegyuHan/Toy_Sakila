package com.toy.sakila.city.adapter.out.persistence;


import com.toy.sakila.city.domain.City;
import com.toy.sakila.common.Mapper;
import com.toy.sakila.country.adapter.out.persistence.CountryPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper
public class CityPersistenceMapper {

    private final CountryPersistenceMapper countryPersistenceMapper;

    public CityJpaEntity mapToJpaEntity(City domain) {
        return CityJpaEntity.builder()
                .cityId(domain.getCityId().getValue())
                .city(domain.getCity())
                .country(countryPersistenceMapper.mapToJpaEntity(domain.getCountry()))
                .build();
    }

    public City mapToDomainEntity(CityJpaEntity entity) {
        return City.builder()
                .cityId(City.CityId.of(entity.getCityId()))
                .city(entity.getCity())
                .country(countryPersistenceMapper.mapToDomainEntity(entity.getCountry()))
                .build();
    }
}