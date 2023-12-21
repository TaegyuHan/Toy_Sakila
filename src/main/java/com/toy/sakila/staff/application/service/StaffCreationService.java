package com.toy.sakila.staff.application.service;

import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.staff.application.port.in.StaffCreationCommand;
import com.toy.sakila.staff.application.port.in.StaffCreationUseCase;
import com.toy.sakila.staff.application.port.out.StaffSavePort;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StaffCreationService implements StaffCreationUseCase {

    private final AddressReadPort addressReadPort;
    private final StoreReadPort storeReadPort;
    private final StaffSavePort staffSavePort;

    @Override
    public Staff create(StaffCreationCommand command) {
        Address address = addressReadPort.findById(Address.AddressId.of(command.getAddressId()));
        Store store = storeReadPort.findById(Store.StoreId.of(command.getStoreId()));

        Staff staff = Staff.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .address(address)
                .email(command.getEmail())
                .store(store)
                .active(command.isActive())
                .username(command.getUsername())
                .password(command.getPassword())
                .build();

        return staffSavePort.save(staff);
    }
}