package com.toy.sakila.address.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddressUpdateCommand {
    String address;
    String address2;
    String district;
    Short cityId;
    String postalCode;
    String phone;
}
