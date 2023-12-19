package com.toy.sakila.store.application.service;

import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.staff.application.port.out.StaffReadPort;
import com.toy.sakila.staff.domain.Staff;
import com.toy.sakila.store.application.port.in.StoreUpdateCommand;
import com.toy.sakila.store.application.port.in.StoreUpdateUseCase;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.application.port.out.StoreSavePort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreUpdateService implements StoreUpdateUseCase {

    private final StoreSavePort storeSavePort;
    private final StoreReadPort storeReadPort;
    private final StaffReadPort staffReadPort;
    private final AddressReadPort addressReadPort;

    @Override
    public Store update(Store.StoreId id, StoreUpdateCommand command) {
        Store store = storeReadPort.findById(id);
        Address address = addressReadPort.findById(Address.AddressId.of(command.getAddressId()));
        Staff staff = staffReadPort.findById(Staff.StaffId.of(command.getManagerStaffId()));
        
        store.setManagerStaff(staff);
        store.setAddress(address);

        return storeSavePort.save(store);
    }
}