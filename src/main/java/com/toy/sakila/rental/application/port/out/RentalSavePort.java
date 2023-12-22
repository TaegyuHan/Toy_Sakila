package com.toy.sakila.rental.application.port.out;

import com.toy.sakila.rental.domain.Rental;

public interface RentalSavePort {
    Rental save(Rental rental);
}
