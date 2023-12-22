package com.toy.sakila.rental.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RentalCreationCommand {
    LocalDateTime rentalDate;
    LocalDateTime returnDate;
    Byte staffId;
    Integer inventoryId;
    Short customerId;
}