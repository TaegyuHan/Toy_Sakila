package com.toy.sakila.customer.application.service;

import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.customer.application.port.in.CustomerCreationCommand;
import com.toy.sakila.customer.application.port.in.CustomerCreationUseCase;
import com.toy.sakila.customer.application.port.out.CustomerSavePort;
import com.toy.sakila.customer.domain.Customer;
import com.toy.sakila.store.application.port.out.StoreReadPort;
import com.toy.sakila.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerCreationService implements CustomerCreationUseCase {

    private final CustomerSavePort customerSavePort;
    private final AddressReadPort addressReadPort;
    private final StoreReadPort storeReadPort;

    @Override
    public Customer create(CustomerCreationCommand command) {
        Address address = addressReadPort.findById(Address.AddressId.of(command.getAddressId()));
        Store store = storeReadPort.findById(Store.StoreId.of(command.getStoreId()));

        Customer customer = Customer.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .store(store)
                .address(address)
                .active(command.isActive())
                .build();

        return customerSavePort.save(customer);
    }
}
