package com.toy.sakila.customer.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CustomerCreationCommand {
    Byte storeId;
    String firstName;
    String lastName;
    String email;
    Short addressId;
    boolean active;
}