package com.toy.sakila.address.application.port.out;

import com.toy.sakila.address.domain.Address;

public interface AddressReadPort {
    Address findById(Address.AddressId id);
}
