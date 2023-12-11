package com.toy.sakila.address.application.service;

import com.toy.sakila.address.application.port.in.AddressUpdateCommand;
import com.toy.sakila.address.application.port.in.AddressUpdateUseCase;
import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.application.port.out.AddressSavePort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.city.application.port.out.CityReadPort;
import com.toy.sakila.city.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressUpdateService implements AddressUpdateUseCase {

    private final AddressSavePort addressSavePort;
    private final CityReadPort cityReadPort;
    private final AddressReadPort addressReadPort;

    @Override
    public Address update(Address.AddressId id, AddressUpdateCommand command) {
        City city = cityReadPort.findById(City.CityId.of(command.getCityId()));
        Address address = addressReadPort.findById(id);

        address.setAddress(command.getAddress());
        address.setAddress2(command.getAddress2());
        address.setDistrict(command.getDistrict());
        address.setCity(city);
        address.setPostalCode(command.getPostalCode());
        address.setPhone(command.getPhone());

        return addressSavePort.save(address);
    }
}