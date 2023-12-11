package com.toy.sakila.address.application.port.in;


import com.toy.sakila.address.domain.Address;
import com.toy.sakila.common.UseCase;

@UseCase
public interface AddressCreationUseCase {
    Address create(AddressCreationCommand command);
}
