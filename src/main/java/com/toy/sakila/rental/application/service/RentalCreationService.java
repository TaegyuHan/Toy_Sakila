package com.toy.sakila.rental.application.service;

import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.inventory.application.port.out.InventoryReadPort;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.rental.application.port.in.RentalCreationCommand;
import com.toy.sakila.rental.application.port.in.RentalCreationUseCase;
import com.toy.sakila.rental.application.port.out.RentalSavePort;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RentalCreationService implements RentalCreationUseCase {

    private final StaffReadPort staffReadPort;
    private final InventoryReadPort inventoryReadPort;
    private final CustomerReadPort customerReadPort;
    private final RentalSavePort rentalSavePort;

    @Override
    public Rental create(RentalCreationCommand command) {
        Staff staff = staffReadPort.findById(Staff.StaffId.of(command.getStaffId()));
        Inventory inventory = inventoryReadPort.findById(Inventory.InventoryId.of(command.getInventoryId()));
        Customer customer = customerReadPort.findById(Customer.CustomerId.of(command.getCustomerId()));
        Rental rental = Rental.builder()
                .rentalDate(command.getRentalDate())
                .returnDate(command.getReturnDate())
                .staff(staff)
                .inventory(inventory)
                .customer(customer)
                .build();
        return rentalSavePort.save(rental);
    }
}