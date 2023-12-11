package com.toy.sakila.address.adapter.out.persistence;

import com.toy.sakila.address.domain.Address;
import com.toy.sakila.city.adapter.out.persistence.CityPersistenceMapper;
import com.toy.sakila.common.Mapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Mapper
public class AddressPersistenceMapper {

    private final CityPersistenceMapper cityPersistenceMapper;

    public AddressJpaEntity mapToJpaEntity(Address domain) {
        return AddressJpaEntity.builder()
                .addressId(domain.getAddressId().getValue())
                .address(domain.getAddress())
                .address2(domain.getAddress2())
                .district(domain.getDistrict())
                .city(cityPersistenceMapper.mapToJpaEntity(domain.getCity()))
                .postalCode(domain.getPostalCode())
                .phone(domain.getPhone())
                .build();
    }

    public Address mapToDomainEntity(AddressJpaEntity jpaEntity) {
        return Address.builder()
                .addressId(Address.AddressId.of(jpaEntity.getAddressId()))
                .address(jpaEntity.getAddress())
                .address2(jpaEntity.getAddress2())
                .district(jpaEntity.getDistrict())
                .city(cityPersistenceMapper.mapToDomainEntity(jpaEntity.getCity()))
                .postalCode(jpaEntity.getPostalCode())
                .phone(jpaEntity.getPhone())
                .build();
    }
}
