package com.toy.sakila.city.application.port.in;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = false)
public class CityUpdateCommand {
    private final String city;
    private final Integer countryId;
}