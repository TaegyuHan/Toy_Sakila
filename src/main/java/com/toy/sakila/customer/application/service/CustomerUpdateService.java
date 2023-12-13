package com.toy.sakila.customer.application.service;

import com.toy.sakila.address.application.port.out.AddressReadPort;
import com.toy.sakila.address.domain.Address;
import com.toy.sakila.customer.application.port.in.CustomerUpdateCommand;
import com.toy.sakila.customer.application.port.in.CustomerUpdateUseCase;
import com.toy.sakila.customer.application.port.out.CustomerReadPort;
import com.toy.sakila.customer.application.port.out.CustomerSavePort;
import com.toy.sakila.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerUpdateService implements CustomerUpdateUseCase {

    private final CustomerSavePort customerSavePort;
    private final CustomerReadPort customerReadPort;
    private final AddressReadPort addressReadPort;

    @Override
    public Customer update(Customer.CustomerId id, CustomerUpdateCommand command) {
        Customer customer = customerReadPort.findById(id);
        Address address = addressReadPort.findById(Address.AddressId.of(command.getAddressId()));

        customer.setFirstName(command.getFirstName());
        customer.setLastName(command.getLastName());
        customer.setEmail(command.getEmail());
        customer.setEmail(command.getEmail());
        customer.setAddress(address);
        customer.setActive(command.isActive());

        return customerSavePort.save(customer);
    }
}