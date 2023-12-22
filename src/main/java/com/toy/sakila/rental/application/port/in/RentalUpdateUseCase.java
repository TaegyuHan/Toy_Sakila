package com.toy.sakila.rental.application.port.in;

import com.toy.sakila.rental.domain.Rental;

public interface RentalUpdateUseCase {
    Rental update(Rental.RentalId id, RentalUpdateCommand command);
}
