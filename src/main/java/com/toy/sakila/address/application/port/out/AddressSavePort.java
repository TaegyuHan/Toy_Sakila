package com.toy.sakila.address.application.port.out;

import com.toy.sakila.address.domain.Address;

public interface AddressSavePort {
    Address save(Address address);
}
