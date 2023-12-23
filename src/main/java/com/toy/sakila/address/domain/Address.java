package com.toy.sakila.address.domain;


import com.toy.sakila.city.domain.City;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class Address {
    private final AddressId id;
    private String address;
    private String address2;
    private String district;
    private City city;
    private String postalCode;
    private String phone;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    @Value
    @AllArgsConstructor(staticName = "of")
    public static class AddressId {
        Short value;
    }
}