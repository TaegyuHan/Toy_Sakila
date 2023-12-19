package com.toy.sakila.store.application.port.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class StoreUpdateCommand {
    Byte managerStaffId;
    Short addressId;
}
