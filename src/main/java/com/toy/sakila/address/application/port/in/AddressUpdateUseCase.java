package com.toy.sakila.address.application.port.in;

import com.toy.sakila.address.domain.Address;

public interface AddressUpdateUseCase {
    Address update(Address.AddressId of, AddressUpdateCommand command);
}
