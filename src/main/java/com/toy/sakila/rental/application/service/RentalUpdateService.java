package com.toy.sakila.rental.application.service;

import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.inventory.application.port.out.InventoryReadPort;
import com.toy.sakila.inventory.domain.Inventory;
import com.toy.sakila.rental.application.port.in.RentalUpdateCommand;
import com.toy.sakila.rental.application.port.in.RentalUpdateUseCase;
import com.toy.sakila.rental.application.port.out.RentalReadPort;
import com.toy.sakila.rental.application.port.out.RentalSavePort;
import com.toy.sakila.rental.domain.Rental;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RentalUpdateService implements RentalUpdateUseCase {

    private final RentalReadPort rentalReadPort;
    private final StaffReadPort staffReadPort;
    private final InventoryReadPort inventoryReadPort;
    private final CustomerReadPort customerReadPort;
    private final RentalSavePort rentalSavePort;

    @Override
    public Rental update(Rental.RentalId id, RentalUpdateCommand command) {
        Rental domain = rentalReadPort.findById(id);
        Staff staff = staffReadPort.findById(Staff.StaffId.of(command.getStaffId()));
        Inventory inventory = inventoryReadPort.findById(Inventory.InventoryId.of(command.getInventoryId()));
        Customer customer = customerReadPort.findById(Customer.CustomerId.of(command.getCustomerId()));

        domain.setRentalDate(command.getRentalDate());
        domain.setReturnDate(command.getReturnDate());
        domain.setStaff(staff);
        domain.setInventory(inventory);
        domain.setCustomer(customer);

        return rentalSavePort.save(domain);
    }
}