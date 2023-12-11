package com.toy.sakila.address.adapter.out.persistence;

import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.application.port.out.AddressSavePort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class AddressPersistenceAdapter
        implements AddressSavePort, AddressReadPort {

    private final SpringDataAddressRepository springDataAddressRepository;
    private final AddressPersistenceMapper mapper;

    @Override
    public Address save(Address address) {
        return Optional.of(address)
                .map(mapper::mapToJpaEntity)
                .map(springDataAddressRepository::save)
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }

    @Override
    public Address findById(Address.AddressId id) {
        return springDataAddressRepository.findById(id.getValue())
                .map(mapper::mapToDomainEntity)
                .orElseThrow();
    }
}