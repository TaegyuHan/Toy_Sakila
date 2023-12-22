package com.toy.sakila.rental.application.port.in;

import com.toy.sakila.rental.domain.Rental;

public interface RentalCreationUseCase {
    Rental create(RentalCreationCommand command);
}
