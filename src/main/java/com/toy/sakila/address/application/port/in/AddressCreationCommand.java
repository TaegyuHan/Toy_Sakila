package com.toy.sakila.address.application.port.in;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddressCreationCommand {
    String address;
    String address2;
    String district;
    Long cityId;
    String postalCode;
    String phone;
}
