package com.toy.sakila.store.application.service;

import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.application.port.in.StoreCreationCommand;
import com.toy.sakila.store.application.port.in.StoreCreationUseCase;
import com.toy.sakila.store.application.port.out.StoreSavePort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreCreationService implements StoreCreationUseCase {

    private final StoreSavePort storeSavePort;
    private final StaffReadPort staffReadPort;
    private final AddressReadPort addressReadPort;

    @Override
    public Store create(StoreCreationCommand command) {
        Staff staff = staffReadPort.findById(Staff.StaffId.of(command.getManagerStaffId()));
        Address address = addressReadPort.findById(Address.AddressId.of(command.getAddressId()));

        Store store = Store.builder()
                .managerStaff(staff)
                .address(address)
                .build();

        return storeSavePort.save(store);
    }
}
