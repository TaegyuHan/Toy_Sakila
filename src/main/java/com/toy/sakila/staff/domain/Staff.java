package com.toy.sakila.staff.domain;

import com.toy.sakila.address.domain.Address;
import com.toy.sakila.store.domain.Store;
import lombok.*;

import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Staff {
    private final StaffId id;
    private String firstName;
    private String lastName;
    private Address address;
    private Store store;
    private Blob picture;
    private String email;
    private boolean active;
    private String username;
    private String password;
    private LocalDateTime lastUpdate;
    private LocalDateTime createdDate;
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class StaffId {
        Byte value;
    }
}
