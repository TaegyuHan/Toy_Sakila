package com.toy.sakila.staff.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.sql.Blob;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class StaffCreationCommand {
    String firstName;
    String lastName;
    Short addressId;
    Blob picture;
    String email;
    Byte storeId;
    boolean active;
    String username;
    String password;
}
