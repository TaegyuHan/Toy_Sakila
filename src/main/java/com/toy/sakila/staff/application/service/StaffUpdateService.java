package com.toy.sakila.staff.application.service;


import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.staff.application.port.in.StaffUpdateCommand;
import com.toy.sakila.staff.application.port.in.StaffUpdateUseCase;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.application.port.out.StaffSavePort;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StaffUpdateService implements StaffUpdateUseCase {

    private final AddressReadPort addressReadPort;
    private final StoreReadPort storeReadPort;
    private final StaffReadPort staffReadPort;
    private final StaffSavePort staffSavePort;

    @Override
    public Staff update(Staff.StaffId id, StaffUpdateCommand command) {
        Staff domain = staffReadPort.findById(id);
        Address address = addressReadPort.findById(Address.AddressId.of(command.getAddressId()));
        Store store = storeReadPort.findById(Store.StoreId.of(command.getStoreId()));

        domain.setFirstName(command.getFirstName());
        domain.setLastName(command.getLastName());
        domain.setAddress(address);
        domain.setEmail(command.getEmail());
        domain.setStore(store);
        domain.setActive(command.isActive());
        domain.setUsername(command.getUsername());
        domain.setPassword(command.getPassword());

        return staffSavePort.save(domain);
    }
}