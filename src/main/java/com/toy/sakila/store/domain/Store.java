package com.toy.sakila.store.domain;

import com.toy.sakila.address.domain.Address;
import com.toy.sakila.staff.domain.Staff;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Store {
    private final StoreId id;
    private Staff managerStaff;
    private Address address;
    private LocalDateTime lastUpdate;
    private LocalDateTime createdDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class StoreId {
        Byte value;
    }
}