package com.toy.sakila.address.application.service;

import com.toy.sakila.address.application.port.in.AddressCreationCommand;
import com.toy.sakila.address.application.port.in.AddressCreationUseCase;
import com.toy.sakila.address.application.port.out.AddressSavePort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.city.application.port.out.CityReadPort;
import com.toy.sakila.city.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressCreationService implements AddressCreationUseCase {

    private final AddressSavePort addressSavePort;
    private final CityReadPort cityReadPort;

    @Override
    public Address create(AddressCreationCommand command) {
        City city = cityReadPort.findById(City.CityId.of(command.getCityId()));
        Address address = Address.builder()
                .address(command.getAddress())
                .address2(command.getAddress2())
                .district(command.getDistrict())
                .city(city)
                .postalCode(command.getPostalCode())
                .phone(command.getPhone())
                .build();

        return addressSavePort.save(address);
    }
}
