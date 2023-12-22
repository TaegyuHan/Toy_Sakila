package com.toy.sakila.rental.domain;


import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.staff.domain.Staff;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Rental {

    private RentalId id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Staff staff;
    private Inventory inventory;
    private Customer customer;
    private LocalDateTime lastUpdate;
    private LocalDateTime createdDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class RentalId {
        Integer value;
    }
}
